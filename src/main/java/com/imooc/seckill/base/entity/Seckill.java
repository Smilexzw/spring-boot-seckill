package com.imooc.seckill.base.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author xuzhangwang
 * @date 2019/7/28
 */
@Data
public class Seckill {
    private long seckillId;
    private String name;
    private int number;
    private Date startTime;
    private Date endTime;
    private Date createTime;
    private int version;
}
