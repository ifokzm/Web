package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cache.redis.RedisUtil;

@Service
public class BaseService
{
    @Autowired
    private RedisUtil cache;
    
    public RedisUtil getCache()
    {
        return cache;
    }
    
    public void setCache(RedisUtil cache)
    {
        this.cache = cache;
    }
    
}
