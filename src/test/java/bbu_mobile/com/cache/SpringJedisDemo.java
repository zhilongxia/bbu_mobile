package bbu_mobile.com.cache;

import java.io.FileNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

/**
 * 
 * @author steel
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/redis/spring-jedis.xml")
public class SpringJedisDemo {

	static {
		try {
			Log4jConfigurer.initLogging("classpath:properties/log4j.properties");
		} catch (FileNotFoundException ex) {
			System.err.println("Cannot Initialize log4j");
		}
	}

	@Autowired
	RedisTemplate jedisTemplate;

	@Test
	public void putAndGet() {
		jedisTemplate.opsForHash().put("user", "name", "张三");
		Object name = jedisTemplate.opsForHash().get("user", "name");
		System.out.println(name);
	}
}