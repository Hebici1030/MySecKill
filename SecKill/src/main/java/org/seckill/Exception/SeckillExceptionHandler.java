package org.seckill.Exception;

import org.seckill.enums.SeckillEnum;
import org.seckill.vo.SeckillResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class SeckillExceptionHandler {
    @ExceptionHandler(NoSeckillException.class)
    @ResponseBody
    public SeckillResult noseckillHandler(NoSeckillException e){
        return new SeckillResult(false, SeckillEnum.NO_SUCH_SECKILL.getInfo());
    }
}

