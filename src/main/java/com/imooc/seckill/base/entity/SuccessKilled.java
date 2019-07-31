package com.imooc.seckill.base.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 成功秒杀类
 * @author xuzhangwang
 * @date 2019/7/28
 */
@Data
public class SuccessKilled {

    private long seckillId;
    private long userId;
    private short state;
    private Timestamp createTime;
}
