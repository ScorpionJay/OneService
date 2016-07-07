package com.one.configure;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @author jay
 * @since 2016年6月16日
 */
@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {
	

	
	
}