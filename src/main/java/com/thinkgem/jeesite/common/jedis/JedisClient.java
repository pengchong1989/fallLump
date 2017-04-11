package com.thinkgem.jeesite.common.jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 
 * @author huzm 
 * @version 1.0.0
 * @since 2016/07/05
 *
 */
public interface JedisClient {
	/**
	 * 获取缓存String
	 * @param key
	 * @return
	 */
	String get(String key);
	/**
	 * 获取缓存Object
	 * @param key
	 * @return
	 */
	Object getObject(String key);
	/**
	 * 设置String值 和缓存时间
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @return
	 */
	String set(String key, String value, int cacheSeconds);
	/**
	 * 设置Object值和缓存时间
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @return
	 */
	String setObject(String key, Object value, int cacheSeconds);
	/**
	 * 获取List<String>缓存
	 * @param key
	 * @return
	 */
	List<String> getList(String key);
	/**
	 * 获取List<Object>缓存
	 * @param key
	 * @return
	 */
	List<Object> getObjectList(String key);
	/**
	 * 设置List<String>并设定缓存时间
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @return
	 */
	long setList(String key, List<String> value, int cacheSeconds);
	/**
	 * 设置List<Object>并设定缓存时间
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @return
	 */
	long setObjectList(String key, List<Object> value, int cacheSeconds);
	/**
	 * List<String> 添加元素
	 * @param key
	 * @param value
	 * @return
	 */
	long listAdd(String key, String... value);
	/**
	 * 缓存的List<Object>添加元素
	 * @param key
	 * @param value
	 * @return
	 */
	long listObjectAdd(String key, Object... value);
	
	/**
	 * 获取set<String>缓存
	 * @param key
	 * @return
	 */
	Set<String> getSet(String key);
	/**
	 * 获取Set<Object>缓存
	 * @param key
	 * @return
	 */
	Set<Object> getObjectSet(String key);
	/**
	 * 设置Set<String>缓存并设置过期时间
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @return
	 */
	long setSet(String key, Set<String> value, int cacheSeconds);
	/**
	 * 设置Set<Object>缓存并设置过期时间
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @return
	 */
	long setObjectSet(String key, Set<Object> value, int cacheSeconds);
	/**
	 * 向Set<String>缓存中添加元素
	 * @param key
	 * @param value
	 * @return
	 */
	long setSetAdd(String key, String... value);
	/**
	 * 向Set<Object>缓存中添加元素
	 * @param key
	 * @param value
	 * @return
	 */
	long setSetObjectAdd(String key, Object... value);
	/**
	 * 获取Map<String,String>缓存
	 * @param key
	 * @return
	 */
	Map<String, String> getMap(String key);
	/**
	 * 获取Map<String,Object>缓存
	 * @param key
	 * @return
	 */
	Map<String, Object> getObjectMap(String key);
	/**
	 * 设置Map<String,String>缓存和过期时间
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @return
	 */
	String setMap(String key, Map<String, String> value, int cacheSeconds);
	/**
	 * 设置Map<String,Object>缓存和过期时间
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @return
	 */
	String setObjectMap(String key, Map<String, Object> value, int cacheSeconds);
	/**
	 * 向缓存Map<String,String>中添加元素
	 * @param key
	 * @param value
	 * @return
	 */
	String mapPut(String key, Map<String, String> value);
	/**
	 * 向缓存Map<String,Object>中添加元素
	 * @param key
	 * @param value
	 * @return
	 */
	String mapObjectPut(String key, Map<String, Object> value);
	/**
	 * 从缓存Map<String,String>中删除某个特定的元素
	 * @param key
	 * @param mapKey
	 * @return
	 */
	long mapRemove(String key, String mapKey);
	/**
	 * 从缓存Map<String,Object>中删除某个特定元素
	 * @param key
	 * @param mapKey
	 * @return
	 */
	long mapObjectRemove(String key, String mapKey);
	/**
	 * 校验Map<String,String>缓存中是否存在某个特定元素
	 * @param key
	 * @param mapKey
	 * @return
	 */
	boolean mapExists(String key, String mapKey);
	/**
	 * 校验Map<String,Object>缓存中是否存在某个特定元素
	 * @param key
	 * @param mapKey
	 * @return
	 */
	boolean mapObjectExists(String key, String mapKey);
	/**
	 * 删除缓存
	 * @param key
	 * @return
	 */
	long del(String key);
	/**
	 * 根据key前缀删除缓存
	 * @param keyPrefix
	 */
	void delByPrefix(String keyPrefix);
	/**
	 * 删除缓存
	 * @param key
	 * @return
	 */
	long delObject(String key);
	/**
	 * 校验缓存是否存在
	 * @param key
	 * @return
	 */
	boolean exists(String key);
	/**
	 * 校验缓存是否存在
	 * @param key
	 * @return
	 */
	boolean existsObject(String key);
	
	/**
	 * 
	* @Title: incr
	* @Description: TODO(返回一个整数，增加后键的值)
	* @author luowenchao.com
	* @date 2017年2月13日 下午3:40:23
	* @param @param key
	* @param @return    设定文件
	* @return long    返回类型
	* @throws
	 */
	long incr(String key);
}
