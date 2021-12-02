package com.goke.config.helper;

import com.goke.demo.SpringContextHolder;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.codec.SmileJacksonCodec;

/**
 * @author 时尚绿坊（北京）生态科技有限公司
 */
@Slf4j
@UtilityClass
public final class RedissonHelper {
	/**
	 * 获取redissonClient
	 *
	 * @return
	 */
	public static RedissonClient redissonClient() {
		return SpringContextHolder.getBean(RedissonClient.class);
	}

	/**
	 * 获取默认转码规则，如果没有配置使用jackson转码
	 *
	 * @return
	 */
	public static Codec codec() {
		Codec codec = SpringContextHolder.getBean(Codec.class);
		if (codec == null) {
			return new SmileJacksonCodec();
		}
		return codec;
	}
}
