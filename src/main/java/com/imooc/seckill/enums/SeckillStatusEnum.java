package com.imooc.seckill.enums;

import com.imooc.seckill.base.entity.Seckill;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 秒杀状态枚举类
 * @author xuzhangwang
 * @date 2019/7/28
 */

@Getter
public enum  SeckillStatusEnum {
    /**
     * 状态
     */
    SUCCESS(1, "秒杀成功！！！"),
    END(0, "秒杀结束"),
    MUCH(2, "哎呦喂, 人也太多了，请稍后！！！"),
    REPEAT_KILL(-1, "重复秒杀"),
    INNER_ERROR(-2, "系统异常"),
    DATE_REWRITE(-3, "数据篡改");


    private int state;

    private String info;


    SeckillStatusEnum(int state, String info) {
        this.state = state;
        this.info = info;
    }


}
