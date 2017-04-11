package com.thinkgem.jeesite.common.jedis.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.thinkgem.jeesite.common.jedis.JedisClient;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.StringUtils;

public class JedisClientCluster implements JedisClient {
	
	private static Logger logger = LoggerFactory.getLogger(JedisClientCluster.class);
	
	@Autowired
	private JedisCluster jedisCluster;
	
	@Override
	public String get(String key) {
		String value = null;
		try {
			if (jedisCluster.exists(key)) {
				value = jedisCluster.get(key);
				value = StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
				logger.debug("get {} = {}", key, value);
			}
		} catch (Exception e) {
			logger.warn("get {} = {}", key, value, e);
		} 
		return value;
	}

	@Override
	public Object getObject(String key) {
		Object value = null;
		try {
			if (jedisCluster.exists(key)) {
				value = toObject(jedisCluster.get(key));
				logger.debug("getObject {} = {}", key, value);
			}
		} catch (Exception e) {
			logger.warn("getObject {} = {}", key, value, e);
		}
		return value;
	}

	@Override
	public String set(String key, String value, int cacheSeconds) {
		String result = null;
		try {
			result = jedisCluster.set(key, value);
			if (cacheSeconds != 0) {
				jedisCluster.expire(key, cacheSeconds);
			}
			logger.debug("set {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("set {} = {}", key, value, e);
		}
		return result;
	}

	@Override
	public String setObject(String key, Object value, int cacheSeconds) {
		String result = null;
		try {
			result = jedisCluster.set(key, toJson(value));
			if (cacheSeconds != 0) {
				jedisCluster.expire(key, cacheSeconds);
			}
			logger.debug("setObject {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("setObject {} = {}", key, value, e);
		}
		return result;
	}

	@Override
	public List<String> getList(String key) {
		List<String> value = null;
		try {
			if (jedisCluster.exists(key)) {
				value = jedisCluster.lrange(key, 0, -1);
				logger.debug("getList {} = {}", key, value);
			}
		} catch (Exception e) {
			logger.warn("getList {} = {}", key, value, e);
		}
		return value;
	}

	@Override
	public List<Object> getObjectList(String key) {
		List<Object> value = null;
		try {
			if (jedisCluster.exists(key)) {
				List<String> list = jedisCluster.lrange(key, 0, -1);
				value = Lists.newArrayList();
				for (String str : list){
					value.add(toObject(str));
				}
				logger.debug("getObjectList {} = {}", key, value);
			}
		} catch (Exception e) {
			logger.warn("getObjectList {} = {}", key, value, e);
		}
		return value;
	}

	@Override
	public long setList(String key, List<String> value, int cacheSeconds) {
		long result = 0;
		try {
			if (jedisCluster.exists(key)) {
				jedisCluster.del(key);
			}
			result = jedisCluster.rpush(key, (String[])value.toArray());
			if (cacheSeconds != 0) {
				jedisCluster.expire(key, cacheSeconds);
			}
			logger.debug("setList {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("setList {} = {}", key, value, e);
		}
		return result;
	}

	@Override
	public long setObjectList(String key, List<Object> value, int cacheSeconds) {
		long result = 0;
		try {
			if (jedisCluster.exists(key)) {
				jedisCluster.del(key);
			}
			List<String> list = Lists.newArrayList();
			for (Object o : value){
				list.add(toJson(o));
			}
			result = jedisCluster.rpush(key, (String[])list.toArray());
			if (cacheSeconds != 0) {
				jedisCluster.expire(key, cacheSeconds);
			}
			logger.debug("setObjectList {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("setObjectList {} = {}", key, value, e);
		}
		return result;
	}

	@Override
	public long listAdd(String key, String... value) {
		long result = 0;
		try {
			result = jedisCluster.rpush(key, value);
			logger.debug("listAdd {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("listAdd {} = {}", key, value, e);
		} 
		return result;
	}

	@Override
	public long listObjectAdd(String key, Object... value) {
		long result = 0;
		try {
			List<String> list = Lists.newArrayList();
			for (Object o : value){
				list.add(toJson(o));
			}
			result = jedisCluster.rpush(key, (String[])list.toArray());
			logger.debug("listObjectAdd {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("listObjectAdd {} = {}", key, value, e);
		}
		return result;
	}

	@Override
	public Set<String> getSet(String key) {
		Set<String> value = null;
		try {
			if (jedisCluster.exists(key)) {
				value = jedisCluster.smembers(key);
				logger.debug("getSet {} = {}", key, value);
			}
		} catch (Exception e) {
			logger.warn("getSet {} = {}", key, value, e);
		}
		return value;
	}

	@Override
	public Set<Object> getObjectSet(String key) {
		Set<Object> value = null;
		try {
			if (jedisCluster.exists(key)) {
				value = Sets.newHashSet();
				Set<String> set = jedisCluster.smembers(key);
				for (String str : set){
					value.add(toObject(str));
				}
				logger.debug("getObjectSet {} = {}", key, value);
			}
		} catch (Exception e) {
			logger.warn("getObjectSet {} = {}", key, value, e);
		}
		return value;
	}

	@Override
	public long setSet(String key, Set<String> value, int cacheSeconds) {
		long result = 0;
		try {
			if (jedisCluster.exists(key)) {
				jedisCluster.del(key);
			}
			result = jedisCluster.sadd(key, (String[])value.toArray());
			if (cacheSeconds != 0) {
				jedisCluster.expire(key, cacheSeconds);
			}
			logger.debug("setSet {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("setSet {} = {}", key, value, e);
		}
		return result;
	}

	@Override
	public long setObjectSet(String key, Set<Object> value, int cacheSeconds) {
		long result = 0;
		try {
			if (jedisCluster.exists(key)) {
				jedisCluster.del(key);
			}
			Set<String> set = Sets.newHashSet();
			for (Object o : value){
				set.add(toJson(o));
			}
			result = jedisCluster.sadd(key, (String[])set.toArray());
			if (cacheSeconds != 0) {
				jedisCluster.expire(key, cacheSeconds);
			}
			logger.debug("setObjectSet {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("setObjectSet {} = {}", key, value, e);
		}
		return result;
	}

	@Override
	public long setSetAdd(String key, String... value) {
		long result = 0;
		try {
			result = jedisCluster.sadd(key, value);
			logger.debug("setSetAdd {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("setSetAdd {} = {}", key, value, e);
		}
		return result;
	}

	@Override
	public long setSetObjectAdd(String key, Object... value) {
		long result = 0;
		try {
			Set<String> set = Sets.newHashSet();
			for (Object o : value){
				set.add(toJson(o));
			}
			result = jedisCluster.rpush(key, (String[])set.toArray());
			logger.debug("setSetObjectAdd {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("setSetObjectAdd {} = {}", key, value, e);
		}
		return result;
	}

	@Override
	public Map<String, String> getMap(String key) {
		Map<String, String> value = null;
		try {
			if (jedisCluster.exists(key)) {
				value = jedisCluster.hgetAll(key);
				logger.debug("getMap {} = {}", key, value);
			}
		} catch (Exception e) {
			logger.warn("getMap {} = {}", key, value, e);
		}
		return value;
	}

	@Override
	public Map<String, Object> getObjectMap(String key) {
		Map<String, Object> value = null;
		try {
			if (jedisCluster.exists(key)) {
				value = Maps.newHashMap();
				Map<String, String> map = jedisCluster.hgetAll(key);
				for (Map.Entry<String, String> e : map.entrySet()){
					value.put(e.getKey(), toObject(e.getValue()));
				}
				logger.debug("getObjectMap {} = {}", key, value);
			}
		} catch (Exception e) {
			logger.warn("getObjectMap {} = {}", key, value, e);
		}
		return value;
	}

	@Override
	public String setMap(String key, Map<String, String> value, int cacheSeconds) {
		String result = null;
		try {
			if (jedisCluster.exists(key)) {
				jedisCluster.del(key);
			}
			result = jedisCluster.hmset(key, value);
			if (cacheSeconds != 0) {
				jedisCluster.expire(key, cacheSeconds);
			}
			logger.debug("setMap {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("setMap {} = {}", key, value, e);
		}
		return result;
	}

	@Override
	public String setObjectMap(String key, Map<String, Object> value,int cacheSeconds) {
		String result = null;
		try {
			if (jedisCluster.exists(key)) {
				jedisCluster.del(key);
			}
			Map<String, String> map = Maps.newHashMap();
			for (Map.Entry<String, Object> e : value.entrySet()){
				map.put(e.getKey(), toJson(e.getValue()));
			}
			result = jedisCluster.hmset(key, (Map<String, String>)map);
			if (cacheSeconds != 0) {
				jedisCluster.expire(key, cacheSeconds);
			}
			logger.debug("setObjectMap {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("setObjectMap {} = {}", key, value, e);
		}
		return result;
	}

	@Override
	public String mapPut(String key, Map<String, String> value) {
		String result = null;
		try {
			result = jedisCluster.hmset(key, value);
			logger.debug("mapPut {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("mapPut {} = {}", key, value, e);
		}
		return result;
	}

	@Override
	public String mapObjectPut(String key, Map<String, Object> value) {
		String result = null;
		try {
			Map<String, String> map = Maps.newHashMap();
			for (Map.Entry<String, Object> e : value.entrySet()){
				map.put(e.getKey(), toJson(e.getValue()));
			}
			result = jedisCluster.hmset(key, (Map<String, String>)map);
			logger.debug("mapObjectPut {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("mapObjectPut {} = {}", key, value, e);
		}
		return result;
	}

	@Override
	public long mapRemove(String key, String mapKey) {
		long result = 0;
		try {
			result = jedisCluster.hdel(key, mapKey);
			logger.debug("mapRemove {}  {}", key, mapKey);
		} catch (Exception e) {
			logger.warn("mapRemove {}  {}", key, mapKey, e);
		}
		return result;
	}

	@Override
	public long mapObjectRemove(String key, String mapKey) {
		long result = 0;
		try {
			result = jedisCluster.hdel(key, mapKey);
			logger.debug("mapObjectRemove {}  {}", key, mapKey);
		} catch (Exception e) {
			logger.warn("mapObjectRemove {}  {}", key, mapKey, e);
		}
		return result;
	}

	@Override
	public boolean mapExists(String key, String mapKey) {
		boolean result = false;
		try {
			result = jedisCluster.hexists(key, mapKey);
			logger.debug("mapExists {}  {}", key, mapKey);
		} catch (Exception e) {
			logger.warn("mapExists {}  {}", key, mapKey, e);
		}
		return result;
	}

	@Override
	public boolean mapObjectExists(String key, String mapKey) {
		boolean result = false;
		try {
			result = jedisCluster.hexists(key, mapKey);
			logger.debug("mapObjectExists {}  {}", key, mapKey);
		} catch (Exception e) {
			logger.warn("mapObjectExists {}  {}", key, mapKey, e);
		}
		return result;
	}

	@Override
	public long del(String key) {
		long result = 0;
		try {
			if (jedisCluster.exists(key)){
				result = jedisCluster.del(key);
				logger.debug("del {}", key);
			}else{
				logger.debug("del {} not exists", key);
			}
		} catch (Exception e) {
			logger.warn("del {}", key, e);
		}
		return result;
	}

	@Override
	public void delByPrefix(String keyPrefix) {
		try {
			Set<String> keySet = getkeys(keyPrefix+"*");
			for(String key : keySet){
				delObject(key);
			}
		} catch (Exception e) {
			logger.warn("del {}", keyPrefix, e);
		}
	}

	private Set<String> getkeys(String pattern) {
		 logger.debug("Start getting keys...");
		 Set<String> keys = Sets.newHashSet();
		 Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
		 for(String k : clusterNodes.keySet()){
			 logger.debug("Getting keys from: {}", k);
			 JedisPool jp = clusterNodes.get(k);  
	         Jedis jedis = jp.getResource();
	         try {  
	                keys.addAll(jedis.keys(pattern));  
	            } catch(Exception e){  
	                logger.error("Getting keys error: {}", e);  
	            } finally{  
	                logger.debug("Connection closed.");  
	                jedis.close();
	            }
	         logger.debug("Keys gotten!"); 
		 }
		return keys;
	}

	@Override
	public long delObject(String key) {
		long result = 0;
		try {
			if (jedisCluster.exists(key)){
				result = jedisCluster.del(key);
				logger.debug("delObject {}", key);
			}else{
				logger.debug("delObject {} not exists", key);
			}
		} catch (Exception e) {
			logger.warn("delObject {}", key, e);
		}
		return result;
	}

	@Override
	public boolean exists(String key) {
		boolean result = false;
		try {
			result = jedisCluster.exists(key);
			logger.debug("exists {}", key);
		} catch (Exception e) {
			logger.warn("exists {}", key, e);
		}
		return result;
	}

	@Override
	public boolean existsObject(String key) {
		boolean result = false;
		try {
			result = jedisCluster.exists(key);
			logger.debug("existsObject {}", key);
		} catch (Exception e) {
			logger.warn("existsObject {}", key, e);
		}
		return result;
	}
	
	@Override
	public long incr(String key) {
		return jedisCluster.incr(key);
	}
	
	/**
	 * Json转换Object
	 * @param key
	 * @return
	 */
	private Object toObject(String jsonString){
		return JsonMapper.fromJsonString(jsonString, Object.class);
	}
	
	/**
	 * Object转换Json类型
	 * @param key
	 * @return
	 */
	private String toJson(Object object){
    	return JsonMapper.toJsonString(object);
	}

}
