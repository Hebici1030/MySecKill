package org.seckill.entity;

import lombok.Data;

import java.util.Date;
@Data
public class SuccessKilled {
    private Long seckillId;
    private Long userPhone;
    private short state;
    private Date createTime;
    private SecKill secKill;
}
