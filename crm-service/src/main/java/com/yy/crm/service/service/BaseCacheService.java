package com.yy.crm.service.service;

import java.util.List;
import java.util.Map;

/**
 * @author luyuanyuan on 2018/3/1.
 */
public interface BaseCacheService {

    void set(String key, String value);

    //exTime的单位是秒
    void setEx(String key,String value,long exTime);

    String get(String key);

    void append(String key, String value);

    void del(String key);

    void expire(String key, long seconds);

    /**
     * 获取过期时间
     */
    Long ttl(String key);

    /**
     * 右侧插入list，列表追加
     *
     * @param key
     * @param list
     * @return
     */
    <T> long rPush(String key, List<T> list);

    /**
     * 获取
     *
     * @param key
     * @param index
     * @return
     */
    <T> T lindex(String key, long index);

    /**
     * 获取list集合中的连续某几项
     *
     * @return
     */
    <T> List<T> lRange(String key, long start, long end);

    /**
     * 保留list区间
     *
     * @param key
     * @param start
     * @param end
     */
    void lTrim(String key, long start, long end);

    /**
     * 替换列表
     *
     * @param key
     * @param list
     * @return
     */
    <T> void setList(String key, List<T> list);

    /**
     * 设置map缓存
     *
     * @param key
     * @param map
     */
    void setHmap(String key, Map<String, Object> map);

    /**
     * 获取map缓存
     *
     * @param key
     * @return
     */
    Map<String, Object> getHmap(String key);

    /**
     * 取消token失效时间
     *
     * @param key
     * @return
     */
    void setPersist(String key);

    /**
     * 判断是否失效
     *
     * @param key
     * @return
     */
    boolean isExist(String key);
}
