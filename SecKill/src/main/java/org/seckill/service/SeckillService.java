package org.seckill.service;

import org.seckill.vo.Exposer;

public interface SeckillService {
    //秒杀服务
    /**
     * 1.秒杀开启
     * 2.输出所有秒杀商品
     * 3.输入秒杀订单
     * 4.查询单个秒杀商品
     */
    public Exposer exportSeckillUrl(Long seckillId);
}
