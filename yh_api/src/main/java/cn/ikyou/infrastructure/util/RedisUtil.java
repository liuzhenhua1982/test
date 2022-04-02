package cn.ikyou.infrastructure.util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
	
	protected Logger logger = LoggerFactory.getLogger(RedisUtil.class);
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public RedisUtil() {
		
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	 
	public boolean expire(String key, long time) {
		try {
			if (time > 0L) {
				this.redisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return false;
		}
	}

 
	public long getExpire(String key) {
		return this.redisTemplate.getExpire(key, TimeUnit.SECONDS);
	}

 
	public boolean hasKey(String key) {
		try {
			return this.redisTemplate.hasKey(key);
		} catch (Exception e) {
			this.logger.error(ExceptionUtils.getStackTrace(e));
			return false;
		}
	}
 
	@SuppressWarnings("unchecked")
	public void del(String... key) {
		if (key != null && key.length > 0) {
			if (key.length == 1) {
				this.redisTemplate.delete(key[0]);
			} else {
				this.redisTemplate.delete(CollectionUtils.arrayToList(key));
			}
		}
	}

	public Object get(String key) {
		return key == null ? null : this.redisTemplate.opsForValue().get(key);
	}

	public boolean set(String key, Object value) {
		try {
			this.redisTemplate.opsForValue().set(key, value);
			return true;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return false;
		}
	}

	public boolean set(String key, Object value, long time) {
		try {
			if (time > 0L)
				this.redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
			else
				set(key, value);
			return true;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return false;
		}
	}
 
}