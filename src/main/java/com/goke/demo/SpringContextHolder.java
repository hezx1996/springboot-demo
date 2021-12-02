package com.goke.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author 时尚绿坊（北京）生态科技有限公司
 */
@Component
public class SpringContextHolder implements DisposableBean, ApplicationContextAware {
	private static ApplicationContext context;

	@Override
	public void destroy() throws Exception {
		SpringContextHolder.context = null;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextHolder.context = applicationContext;
	}

	/**
	 * 根据名称查询组件
	 *
	 * @param name 组件名称
	 * @param <T>
	 * @return
	 */
	public static <T> T getBean(String name) {
		return (T) context.getBean(name);
	}

	/**
	 * 根据类型查询组件
	 *
	 * @param clazz 类型
	 * @param <T>
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		return context.getBean(clazz);
	}

}
