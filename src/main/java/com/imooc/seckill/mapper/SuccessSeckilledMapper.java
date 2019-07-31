package com.imooc.seckill.mapper;

import com.imooc.seckill.base.entity.SuccessKilled;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @author xuzhangwang
 * @date 2019/7/28
 */
public interface SuccessSeckilledMapper {

    @Insert("insert into success_killed (seckill_id, user_id, state, create_time) values (#{seckillId}, #{userId}, #{state}, #{createTime})")
    void save(SuccessKilled successKilled);


    @Select("select count(*) from success_killed where seckill_id=#{seckillId}")
    long getSeckkillCount(long seckillId);

}
