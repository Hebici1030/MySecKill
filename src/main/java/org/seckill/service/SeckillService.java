package org.seckill.service;

import com.github.pagehelper.PageInfo;
import org.seckill.entity.SecKill;
import org.seckill.vo.Exposer;
import org.seckill.vo.SeckillExecution;
import org.seckill.vo.SeckillResult;

public interface SeckillService {
    //秒杀服务
    /**
     * 1.秒杀开启
     * 2.输出所有秒杀商品
     * 3.输入秒杀订单
     * 4.查询单个秒杀商品
     */
    public Exposer exportSeckillUrl(Long seckillId);

    public SeckillExecution executeSeckill(Long seckillId, String MD5, Long userPhone) ;
    
    public PageInfo<SecKill> queryAll(Long SeckillId);

    /**
     *查询单个秒杀记录
     * @param seckillId
     * @return
     */
    SecKill getById(long seckillId);

    String toMD5(Long seckillId);

}
