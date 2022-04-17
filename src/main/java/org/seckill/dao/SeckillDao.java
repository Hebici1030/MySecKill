package org.seckill.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SecKill;

import java.util.Date;
import java.util.List;

@Mapper
public interface SeckillDao {
    //减少库存
    int reduceNumber(@Param("seckillId") long seckillId,
                     @Param("killTime") Date KillTime);
    SecKill queryById(long seckillId);
    /**
     * 根据偏移量查询秒杀商品列表
     * @param offset
     * @param limit
     * @return
     */
//    分页查询
    List<SecKill> queryAll(@Param("offset") int offset,@Param("limit" )int limit);
}
