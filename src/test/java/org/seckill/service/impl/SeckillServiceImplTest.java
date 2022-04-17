package org.seckill.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.service.SeckillService;
import org.seckill.vo.Exposer;
import org.seckill.vo.SeckillExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//给junit的spring配置文件
@ContextConfiguration("classpath:spring/*.xml")
public class SeckillServiceImplTest {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService service;
    @Test
    public void exposeurl(){
        Exposer exposer = service.exportSeckillUrl(1000L);
        System.out.println(exposer);
    }
    @Test
    public void SeckillExection() {
        String s = service.toMD5(1000L);
        SeckillExecution execution = service.executeSeckill(1000L, s.toString(), 17691077923L);
        System.out.println(execution);
    }
    private static String a = "one";
    private static final String LOCK_SUCCESS = a;
    private static final String SET_IF_NOT_EXIST = "NX";
    @Test
    public void test(){
        System.out.println(LOCK_SUCCESS);
        a = "c";
        System.out.println(a.toString());
        System.out.println(LOCK_SUCCESS);
    }
}