package com.imooc.seckill.service;

import com.imooc.seckill.base.api.Result;
import com.imooc.seckill.base.entity.Seckill;

import java.util.List;

/**
 * @author xuzhangwang
 * @date 2019/7/28
 */
public interface ISeckillService {


    Seckill findOne(long seckillId);

    List<Seckill> findSeckills();

    void insert(Seckill seckill);

    void updateById(long seckillId);

    void deleteById(long seckillId);

    long getSeckillCount(long seckillId);


    /**
     * 秒杀一： 不进行任何锁操作
     * @param seckillId
     * @param userId
     * @return
     */
    Result startSeckillWithout(long seckillId, long userId);

}
