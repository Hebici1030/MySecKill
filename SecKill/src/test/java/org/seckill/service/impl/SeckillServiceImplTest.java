package org.seckill.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.service.SeckillService;
import org.seckill.vo.Exposer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
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

}