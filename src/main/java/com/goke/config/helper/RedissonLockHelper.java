package com.goke.config.helper;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;


/**
 * @author 时尚绿坊（北京）生态科技有限公司
 * redissonLock 锁
 *
 */
@Slf4j
@UtilityClass
public final class RedissonLockHelper {
	private static final String LOCK_TITLE = "redisson-lock:";

	/**
	 * 获取锁
	 *
	 * @param lockName 锁名称
	 */
	public static void tryLock(String lockName) {
		String key = LOCK_TITLE + lockName;
		RLock lock = RedissonHelper.redissonClient().getLock(key);
		lock.lock(3, TimeUnit.SECONDS);
	}

	/**
	 * 释放锁
	 *
	 * @param lockName 锁名称
	 */
	public static void relaseLock(String lockName) {
		String key = LOCK_TITLE + lockName;
		RLock lock = RedissonHelper.redissonClient().getLock(key);
		lock.unlock();
	}
	/**
	 * get
	 *
	 * @param key 唯一key
	 */
	public static String getCode(String key) {
		RBucket<String> bucket = RedissonHelper.redissonClient().getBucket(key);
		String code = bucket.getAndSet(key);
		return code;
	}
	/**
	 * set
	 *
	 * @param key 唯一key
	 */
	public static void setCode(String key,String code) {
		RBucket<String> bucket = RedissonHelper.redissonClient().getBucket(key);
		bucket.expire(50L,TimeUnit.SECONDS);
		bucket.set(code,60L,TimeUnit.SECONDS);
	}
}
