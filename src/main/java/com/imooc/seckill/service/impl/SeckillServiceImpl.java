package com.imooc.seckill.service.impl;

import com.imooc.seckill.base.api.Result;
import com.imooc.seckill.base.entity.Seckill;
import com.imooc.seckill.base.entity.SuccessKilled;
import com.imooc.seckill.enums.SeckillStatusEnum;
import com.imooc.seckill.mapper.SeckillMapper;
import com.imooc.seckill.mapper.SuccessSeckilledMapper;
import com.imooc.seckill.service.ISeckillService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author xuzhangwang
 * @date 2019/7/28
 */
@Service
public class SeckillServiceImpl implements ISeckillService{


    @Autowired
    SeckillMapper seckillMapper;

    @Autowired
    SuccessSeckilledMapper successSeckilledMapper;

    @Override
    public Seckill findOne(long seckillId) {
        return seckillMapper.findOne(seckillId);
    }

    @Override
    public List<Seckill> findSeckills() {
        return seckillMapper.findAll();
    }

    @Override
    public void insert(Seckill seckill) {

    }

    @Override
    public void updateById(long seckillId) {

    }

    @Override
    public void deleteById(long seckillId) {

    }

    @Override
    public long getSeckillCount(long seckillId) {
        return successSeckilledMapper.getSeckkillCount(seckillId);
    }

    @Override
    public Result startSeckillWithout(long seckillId, long userId) {
        long number = seckillMapper.getSeckillCount(seckillId);
        if (number > 0) {
            // 开始扣库存
            seckillMapper.subNumber(seckillId);
            // 创建订单
            SuccessKilled killed = new SuccessKilled();
            killed.setSeckillId(seckillId);
            killed.setUserId(userId);
            killed.setState((short) 0);
            killed.setCreateTime(new Timestamp(System.currentTimeMillis()));
            successSeckilledMapper.save(killed);
            // 模拟支付
            return Result.ok(SeckillStatusEnum.SUCCESS);
        } else {
            return Result.error(SeckillStatusEnum.END);
        }
    }
}
