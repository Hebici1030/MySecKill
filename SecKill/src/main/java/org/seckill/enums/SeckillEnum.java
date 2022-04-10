package org.seckill.enums;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
public enum SeckillEnum {
    SECKILL_NOT_START(-3,"秒杀未开始"),
    NO_SUCH_SECKILL("没有此商品");
    private Integer status;
    private String info;
    SeckillEnum(int i, String ino) {
        status = i;
        info = ino;
    }
    SeckillEnum(String info){
        this.info = info;
    }
}
