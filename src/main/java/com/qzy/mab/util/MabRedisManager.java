package com.qzy.mab.util;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.crazycake.shiro.RedisManager;
import org.springframework.data.redis.core.RedisTemplate;

public class MabRedisManager extends RedisManager {

    private static final Log LOG = LogFactory.getLog(MabRedisManager.class);

    @Resource(name = "redisTemplate")
    RedisTemplate<String, byte[]> redisTemplate;

    public MabRedisManager() {
    }

    @Override
    public void init() {
    }

    @Override
    public byte[] get(byte[] key) {
        String keyStr = new String(key);
        byte[] obj = redisTemplate.opsForValue().get(keyStr);
        return obj;
    }

    @Override
    public byte[] set(byte[] key, byte[] value) {
        String keyStr = new String(key);
        redisTemplate.opsForValue().set(keyStr,value);
        return value;
    }

    public byte[] set(byte[] key, byte[] value,long expire,TimeUnit timeUnit) {
        String keyStr = new String(key);
        redisTemplate.opsForValue().set(keyStr,value,expire, timeUnit);
        return value;
    }

    @Override
    public byte[] set(byte[] key, byte[] value, int expire) {
        String keyStr = new String(key);
        redisTemplate.opsForValue().set(keyStr,value,expire);
        return value;
    }

    @Override
    public void del(byte[] key) {
        String keyStr = new String(key);
        redisTemplate.delete(keyStr);
    }

    @Override
    public void flushDB() {
        LOG.info("==>flushDB");
    }

    @Override
    public Long dbSize() {
        Long dbSize = 0L;
        LOG.info("==>dbSize");

        return dbSize;
    }

    @Override
    public Set<byte[]> keys(String pattern) {
        Set<byte[]> keys = null;
        LOG.info("==>keys");
        return keys;
    }

}
