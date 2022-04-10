package org.seckill.service.impl;

import org.apache.commons.logging.LogFactory;
import org.seckill.Exception.NoSeckillException;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.SecKill;
import org.seckill.service.SeckillService;
import org.seckill.vo.Exposer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import sun.security.provider.MD5;

import java.util.Date;

@Service
@Component
public class SeckillServiceImpl implements SeckillService {

    //日志对象
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final String salt = "dadadafdafaUJM(O0";
    @Autowired
    private SeckillDao seckillDao;

    /***
     * 暴漏秒杀地址
     * @param SeckillId
     * @return
     */
    public Exposer exportSeckillUrl(Long SeckillId) {
        SecKill secKill = seckillDao.queryById(SeckillId);
        if(secKill==null){
            //难点：当没有查找到该信息时， 应该为前端返回一个该秒杀商品不存在的信息
            /**
             * 但是此方法返回值为Exposer
             * 1.通过设置flase
             * 2.throw错误包装成SeckillResult
             */
            throw new NoSeckillException();
        }

        Date nowData = new Date();
        Date startTime = secKill.getStartTime();
        Date endTime = secKill.getEndTime();
        if(nowData.before(startTime)||nowData.after(endTime)){
            return new Exposer(SeckillId,startTime,endTime,nowData,false);
        }
        //秒杀开启，返回秒杀商品的id、用给接口加密的md5
        String md5 = toMD5String(SeckillId);
        return new Exposer(SeckillId,true,md5);
    }

    /***
     * 转化 MD5地址发送给前端
     * @param recourse
     * @return
     */
    private String toMD5String(Long recourse){
        String base = recourse + "/" + salt;
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5DigestAsHex;
    }
}
