package org.seckill.enums;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
public enum SeckillExceptionEnum {
    SECKILL_NOT_START(-3,"秒杀未开始"),
    NO_SUCH_SECKILL("没有此商品"),
    MD5_ERRPR("秒杀地址篡改"),
    REPEATE_SECKILL("重复秒杀"),
    SECKILL_CLOSE("秒杀活动过期");
    private Integer status;
    private String info;
    SeckillExceptionEnum(int i, String ino) {
        status = i;
        info = ino;
    }
    SeckillExceptionEnum(String info){
        this.info = info;
    }
}
