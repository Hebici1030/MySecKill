package org.seckill.utils;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class JedisUtils {
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;

    /***
     * 获取分布式锁
     * @param jedis
     * @param lockKey
     * @param RequestId
     * @param expireTime
     * @return
     */
    public static boolean tryGetDistributedLock(Jedis jedis,String lockKey,String RequestId,Integer expireTime){
        String res = jedis.set(lockKey, RequestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        return res.equals(LOCK_SUCCESS);
    }

    public static boolean releaseDistributedLock(Jedis jedis,String lockKey,String ReqestId){


    }
}
