package org.seckill.vo;

import lombok.Data;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillEnum;
@Data
public class SeckillExecution {
    private Long seckillId;
    private SeckillEnum seckillEnum;
    private SuccessKilled successKilled;

    public SeckillExecution(Long seckillId, SeckillEnum seckillEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.seckillEnum = seckillEnum;
        this.successKilled = successKilled;
    }

    public SeckillExecution(Long seckillId, SeckillEnum seckillEnum) {
        this.seckillId = seckillId;
        this.seckillEnum = seckillEnum;
    }
}
