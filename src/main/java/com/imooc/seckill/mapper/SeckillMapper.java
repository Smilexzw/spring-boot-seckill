package com.imooc.seckill.mapper;

import com.imooc.seckill.base.entity.Seckill;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author xuzhangwang
 * @date 2019/7/28
 */
@Mapper
public interface SeckillMapper {

    @Select("select * from seckill where seckill_id=#{seckillId}")
    Seckill findOne(long seckillId);

    @Select("select * from seckill")
    List<Seckill> findAll();

    @Insert("insert into seckill (seckill_id, name, number) values (#{seckillId}, #{name}, #{number})")
    long insert(Seckill seckill);

    @Delete("delete from seckill where seckill_id=#{seckillId}")
    long deleteById(long seckillId);

    @Select("select number from seckill where seckill_id=#{id}")
    long getSeckillCount(long seckillId);

    /**
     * 每次执行秒杀之前都要的之前的库存设置为100
     * 第一个版本只是的为了模拟的数据
     * @param seckillId
     * @return
     */
    @Update("update seckill set number=100 where seckill_id=#{seckillId}")
    long updateNumberById(long seckillId);

    @Update("update seckill set number=number-1 where seckill_id=#{seckillId}")
    long subNumber(long seckillId);


}
