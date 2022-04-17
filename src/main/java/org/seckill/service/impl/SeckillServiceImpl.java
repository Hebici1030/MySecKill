package org.seckill.service.impl;

import com.github.pagehelper.PageInfo;
import org.seckill.Exception.NoSeckillException;
import org.seckill.Exception.SeckillCloseException;
import org.seckill.Exception.SeckillException;
import org.seckill.Exception.SeckillRepeatException;
import org.seckill.dao.SuccessSeckillDao;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillEnum;
import org.seckill.service.SeckillService;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.SecKill;
import org.seckill.vo.Exposer;
import org.seckill.vo.SeckillExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
@Component
public class SeckillServiceImpl implements SeckillService {

    //日志对象
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final String salt = "dadadafdafaUJM(O0";
    //页面大小
    public static final Integer pageSize = 5;
    public static final Integer pageNum =1;
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessSeckillDao successSeckillDao;
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
    //秒杀是否成功 成功：减库存并且增加明细 失败：抛出异常 事务回滚

    /***
     * 秒杀是否成功 成功：减库存并且增加明细 失败：抛出异常 事务回滚
     * @param seckillId
     * @param MD5
     * @param userPhone
     * @return
     */
    @Transactional
    public SeckillExecution executeSeckill(Long seckillId, String MD5, Long userPhone) {
        //业务场景1：重复秒杀
        //业务场景2：更新过程中秒杀结束？
        if(MD5.equals("")||!MD5.equals(toMD5String(seckillId))){
            throw new SeckillException();
        }
        SuccessKilled successKilled = successSeckillDao.queryByIdWithSeckill(seckillId, userPhone);
        if(successKilled!=null){
            throw new SeckillRepeatException();
        }
        Date date = new Date();
        int reduceNumber = seckillDao.reduceNumber(seckillId, date);
        //在sql语句中限定number>0
        //只有秒杀时间过期的情况
        if(reduceNumber<=0){
            throw new SeckillCloseException();
        }
        //秒杀成功记录订单
        while(true){
            int seckill = successSeckillDao.insertSuccessSeckill(seckillId, userPhone);
            if(seckill>0){
                break;
            }
        }
        SuccessKilled successObject = successSeckillDao.queryByIdWithSeckill(seckillId,userPhone);
        return new SeckillExecution(seckillId,SeckillEnum.SECKILL_SUCCESS,successObject) ;
    }


    public PageInfo<SecKill> queryAll(Long SeckillId) {
        List<SecKill> secKills = seckillDao.queryAll(0, 5);
        PageInfo<SecKill> pageInfo = new PageInfo<SecKill>(secKills);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        return pageInfo;
    }

    public SecKill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public String toMD5(Long seckillId) {
        String base = seckillId + "/" + salt;
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5DigestAsHex;
    }

    /***
     * 转化 MD5地址发送给前端
     * @param recourse
     * @return
     */
    public static  String toMD5String(Long recourse){
        String base = recourse + "/" + salt;
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5DigestAsHex;
    }
}
