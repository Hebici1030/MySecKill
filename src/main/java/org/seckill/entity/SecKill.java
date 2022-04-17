package org.seckill.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SecKill {
    private Long seckillId;
    private String name;
    private Long number;
    private Date startTime;
    private Date endTime;
    private Date createTime;
}
