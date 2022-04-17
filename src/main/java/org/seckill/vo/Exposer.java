package org.seckill.vo;

import lombok.Data;

import java.util.Date;
@Data
public class Exposer {
    private Long seckillId;
    private Date startTime;
    private Date endTime;
    private Date notTime;
    public Boolean start;
    //封装判断是否开始时间
    public String MD;

    public Exposer(Long seckillId, Date startTime, Date endTime, Date notTime, Boolean start) {
        this.seckillId = seckillId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.notTime = notTime;
        this.start = start;
    }

    public Exposer(Long seckillId, Boolean start, String MD) {
        this.seckillId = seckillId;
        this.start = start;
        this.MD = MD;
    }
}
