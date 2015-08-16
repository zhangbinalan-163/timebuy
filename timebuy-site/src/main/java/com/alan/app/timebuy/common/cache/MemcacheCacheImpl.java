package com.alan.app.timebuy.common.cache;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * ICACHE的memcache实现
 *
 * Created by zhangbinalan on 15/8/16.
 */
@Component(value = "memcachedCache")
public class MemcacheCacheImpl implements ICache{
    private static Logger logger = LoggerFactory.getLogger(MemcacheCacheImpl.class);

    @Value("#{'${memcache.servers}'.split(',')}")
    private List<String> servers;

    private MemcachedClient client = null;

    @Override
    public void set(String key, String value, int time) {
        try {
            this.client.set(key, time, value);
            if (logger.isDebugEnabled())
                logger.debug("memcache set success,key={}", key);
        }
        catch (Exception e) {
            logger.error("memcache set fail,key={},value={},time={}",key,value,time,e);
        }
    }
    @Override
    public String get(String key) {
        try
        {
            String value = (String)this.client.get(key);
            if (logger.isDebugEnabled()) {
                logger.debug("get from memcache success,key={}", key);
            }
            return value;
        } catch (Exception e) {
            logger.error("get from memcache fail,key={}", key, e);
        }
        return null;
    }

    @Override
    public void delete(String key) {
        try {
            this.client.delete(key);
            if (logger.isDebugEnabled())
                logger.debug("delete from memcache success,key={}", key);
        }
        catch (Exception e) {
            logger.error("delete from memcache fail,key={}", key, e);
        }
    }

    @Override
    public long incr(String key, long count)
    {
        try {
            long value = this.client.incr(key, count, 1L);
            if (logger.isDebugEnabled()) {
                logger.debug("incr from memcache success,key={},new value={}", key,count);
            }
            return value;
        } catch (Exception e) {
            logger.error("incr from memcache fail,key={}", key, e);
        }return 0L;
    }

    @PreDestroy
    public void destroy() throws IOException
    {
        this.client.shutdown();
    }
    @PostConstruct
    public void init() throws IOException {
        Map addressMap = new LinkedHashMap();
        for (String serverStr : this.servers) {
            addressMap.put(getAddress(serverStr), null);
        }
        Object builder = new XMemcachedClientBuilder(addressMap);
        ((MemcachedClientBuilder)builder).setFailureMode(false);
        this.client = ((MemcachedClientBuilder)builder).build();
    }
    private InetSocketAddress getAddress(String s) {
        if (s == null) {
            throw new NullPointerException("Null host");
        }

        String hoststuff = s.trim();
        if (hoststuff.equals("")) {
            throw new IllegalArgumentException("Empty host: ``" + s + "''");
        }

        int finalColon = hoststuff.lastIndexOf(58);
        if (finalColon < 1) {
            throw new IllegalArgumentException("Invalid server ``" + s + "''");
        }

        String hostPart = hoststuff.substring(0, finalColon).trim();
        String portNum = hoststuff.substring(finalColon + 1).trim();

        return new InetSocketAddress(hostPart, Integer.parseInt(portNum));
    }
}
