package org.seckill.dao.cache;

import com.alibaba.fastjson.support.jaxrs.FastJsonProvider;
import com.alibaba.fastjson.support.spring.FastJsonContainer;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.MappedSchema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.mchange.io.impl.LazyReadOnlyMemoryFileImpl;
import org.seckill.entity.SecKill;
import org.seckill.vo.SeckillExecution;
import org.springframework.beans.BeanUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class RedisDao {
    private final JedisPool jedisPool;

    public RedisDao(String ip,Integer port) {
        this.jedisPool = new JedisPool(ip,port);
    }
    private RuntimeSchema<SecKill> schema = RuntimeSchema.createFrom(SecKill.class);

    /***
     * redis缓存获取Seckill对象
     * @param seckillId
     * @return
     */
    public SecKill getSeckill(Long seckillId){
        return getSeckill(seckillId,jedisPool.getResource());
    }
    public SecKill getOrPutSeckill(Long seckillId, Function<Long,SecKill> getDataFromDb){
        String lockKey = "seckill:locks:getSeckill:" + seckillId;
        String lockReqeustId = UUID.randomUUID().toString();
        Jedis jedis = jedisPool.getResource();
        //进行加锁
        try{
            while (true){
                SecKill seckill = getSeckill(seckillId, jedis);
                if(seckill!=null){
                    return null;
                }
                //尝试获取锁
                //设置锁过期时间防止程序待机或崩溃等没有释放锁

            }
        }catch (Exception e){

        }finally {

        }
        return null;
    }
    private SecKill getSeckill(Long seckillId, Jedis jedis){
        boolean hasJedis = jedis!=null;
        try {
            if(!hasJedis){
                jedis = jedisPool.getResource();
            }
            try{
                String Key = getSeckillRedisKey(seckillId);
                //反序列化
                byte[] bytes = jedis.get(Key.getBytes());
                if(bytes!=null){
                    SecKill secKill = schema.newMessage();
                    //反序列化
                    ProtostuffIOUtil.mergeFrom(bytes,secKill,schema);
                    return secKill;
                }
            }finally {
                if(!hasJedis){
                    jedis.close();
                }
            }
        }catch (Exception e){

        }finally {
            jedis.close();
        }
        return null;
    }

    private String getSeckillRedisKey(Long seckillId) {
        return "seckill:" + seckillId;
    }

    public String putSeckillRedis(SecKill secKill){
        return putSeckillRedis(secKill,null);
    }
    /**
     * redis 存入数据
     * @param seckillId
     */
    public String putSeckillRedis(SecKill secKill,Jedis jedis){
        boolean hasJedis = jedis!=null;
        try {
            if(!hasJedis){
                jedis = jedisPool.getResource();
            }
            try{
                String key = getSeckillRedisKey(secKill.getSeckillId());
                byte[] bytes = ProtostuffIOUtil.toByteArray(secKill, schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                String result = jedis.setex(key.getBytes(),60,bytes);
                return result;
            }catch (Exception e){

            }
        }catch (Exception e){

        }finally {
            jedis.close();
        }
        return null;

    }


}
