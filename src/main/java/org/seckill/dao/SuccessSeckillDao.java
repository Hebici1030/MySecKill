package org.seckill.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;
@Mapper
public interface SuccessSeckillDao {
    //联合主键过滤重复
    public int insertSuccessSeckill(@Param("id")Long seckillId,@Param("phone")Long userPhone);
//根据秒杀商品的id查询明细SuccessKilled对象(该对象携带了Seckill秒杀产品对象)
    public SuccessKilled queryByIdWithSeckill(@Param("id")Long seckillId,@Param("phone")Long userPhone);
}
