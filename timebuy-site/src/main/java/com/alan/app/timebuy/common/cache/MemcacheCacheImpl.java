package com.alan.app.timebuy.common.cache;

import com.alan.app.timebuy.common.util.StringUtils;
import net.rubyeye.xmemcached.MemcachedClient;
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
@Component(value = "memcachedCacheImpl")
public class MemcacheCacheImpl implements ICache{
    private static Logger logger = LoggerFactory.getLogger(MemcacheCacheImpl.class);

    @Value("#{'${memcache.servers}'.split(',')}")
    private List<String> servers;

    private MemcachedClient client = null;

    @Override
    public void set(String key, String value, int time) {
        try {
            long startTime=0L;
            if(logger.isDebugEnabled()){
                startTime=System.currentTimeMillis();
            }
            client.set(key, time, value);
            if (logger.isDebugEnabled())
                logger.debug("memcache set success,key={},value={},exp(s)={},time(ms)={}", key,value,time,(System.currentTimeMillis()-startTime));
        }
        catch (Exception e) {
            logger.error("memcache set fail,key={},value={},time={}",key,value,time,e);
        }
    }

    @Override
    public String get(String key) {
        try {
            long startTime=0L;
            if(logger.isDebugEnabled()){
                startTime=System.currentTimeMillis();
            }
            String value = client.get(key);
            if (logger.isDebugEnabled()) {
                logger.debug("get from memcache success,key={},value={},time(ms)={}", key,value,(System.currentTimeMillis()-startTime));
            }
            return value;
        } catch (Exception e) {
            logger.error("get from memcache fail,key={}", key, e);
        }
        return null;
    }
    @Override
    public void setObject(String key, Object value, int time) {
        String jsonString=StringUtils.toJsonString(value);
        set(key,jsonString,time);
    }

    @Override
    public <T> T getObject(String key, Class<T> targetClass) {
        String jsonValue=get(key);
        if(!StringUtils.isEmpty(jsonValue)){
            try {
                return StringUtils.jsonToObject(jsonValue,targetClass);
            } catch (Exception e) {
                logger.error("convert cache string value to object fail,value={},targetClass={}",jsonValue,targetClass.getName());
            }
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
    public long incr(String key, long count,long defaultValue,int time) {
        try {
            long value = client.incr(key, count, 1L,time);
            if (logger.isDebugEnabled()) {
                logger.debug("incr from memcache success,key={},new value={}", key,count);
            }
            return value;
        } catch (Exception e) {
            logger.error("incr from memcache fail,key={}", key, e);
        }
        return 0L;
    }

    /**
     * 关闭client
     * @throws IOException
     */
    @PreDestroy
    public void destroy() throws IOException {
        client.shutdown();
    }

    /**
     * 初始化缓存连接
     * @throws IOException
     */
    @PostConstruct
    public void init() throws IOException {
        Map addressMap = new LinkedHashMap();
        for (String serverStr : this.servers) {
            addressMap.put(getAddress(serverStr), null);
        }
        XMemcachedClientBuilder builder = new XMemcachedClientBuilder(addressMap);
        builder.setFailureMode(false);
        client = builder.build();
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
