package org.seckill.Exception;

import org.seckill.enums.SeckillExceptionEnum;
import org.seckill.vo.SeckillResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class SeckillExceptionHandler {
    @ExceptionHandler(NoSeckillException.class)
    @ResponseBody
    public SeckillResult noseckillHandler(NoSeckillException e){
        return new SeckillResult(false, SeckillExceptionEnum.NO_SUCH_SECKILL.getInfo());
    }
    @ExceptionHandler(SeckillException.class)
    @ResponseBody
    public SeckillResult seckillExecption(SeckillException e){
        return new SeckillResult(false,SeckillExceptionEnum.MD5_ERRPR.getInfo());
    }
    @ExceptionHandler(SeckillRepeatException.class)
    @ResponseBody
    public SeckillResult seckillRepeateExecption(SeckillRepeatException e) {
        return new SeckillResult(false, SeckillExceptionEnum.REPEATE_SECKILL.getInfo());
    }
    @ExceptionHandler(SeckillCloseException.class)
    @ResponseBody
    public SeckillResult seckillCloseException(SeckillCloseException e) {
        return new SeckillResult(false, SeckillExceptionEnum.MD5_ERRPR.getInfo());
    }
}

