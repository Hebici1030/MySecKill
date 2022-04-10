package org.seckill.vo;

import lombok.Data;

@Data
public class SeckillResult <T> {
    private boolean success;
    private String msg;
    private T data;

    public SeckillResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
}
