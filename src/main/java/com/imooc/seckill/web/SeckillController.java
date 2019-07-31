package com.imooc.seckill.web;

import com.imooc.seckill.base.api.Result;
import com.imooc.seckill.base.entity.Seckill;
import com.imooc.seckill.service.ISeckillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzhangwang
 * @date 2019/7/28
 */
@Slf4j
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    private static int corePoolSize = Runtime.getRuntime().availableProcessors();
    //创建线程池  调整队列数 拒绝服务
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, corePoolSize + 1, 10L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(1000));

    @Autowired
    ISeckillService seckillService;

    @RequestMapping("/query/{id}")
    @ResponseBody
    public Seckill findOneSeckill(@PathVariable("id") long seckillId) {
        return seckillService.findOne(seckillId);
    }

    @ResponseBody
    @GetMapping("/query/all")
    public List<Seckill> findSeckils() {
        return seckillService.findSeckills();
    }


    @PostMapping("/startnothing/{id}")
    @ResponseBody
    public Result startNothing(@PathVariable("id") long seckillId) {
        long start = System.currentTimeMillis();
        int skillNum = 1000;
        final CountDownLatch latch = new CountDownLatch(skillNum);
        final long killId = seckillId;
        log.info("开始秒杀方案一: (不使用任何加锁操作)");
        for (int i = 0; i < skillNum; i++) {
            final long userId = i;
            executor.execute(() -> {
                Result result = seckillService.startSeckillWithout(killId, userId);
                System.out.println(result);
                if(result != null){
                    log.info("用户:{}{}",userId,result.get("msg"));
                }else{
                    log.info("用户:{}{}",userId,"哎呦喂，人也太多了，请稍后！");
                }
                latch.countDown();
            });
        }
        try {
            latch.await(); // 等待所有任务结束
            long seckillCount = seckillService.getSeckillCount(seckillId);
            long end = System.currentTimeMillis();
            log.info("一共售出: {}", seckillCount);
            log.info("花费时间 {}", end - start);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }


}
