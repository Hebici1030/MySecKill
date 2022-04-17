package org.seckill.enums;

import lombok.Getter;

@Getter
public enum SeckillEnum {
    SECKILL_SUCCESS(0,"秒杀成功");
    Integer status;
    String msg;

    SeckillEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
