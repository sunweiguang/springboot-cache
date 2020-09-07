package com.tianqiauto.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("deprecation")
@Configuration
public class RedisConfig {

   @Bean
   public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
      RedisTemplate<Object, Object> template = new RedisTemplate<>();
      template.setConnectionFactory(connectionFactory);

      //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
      Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

      ObjectMapper mapper = new ObjectMapper();
      mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
      mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
      serializer.setObjectMapper(mapper);

      template.setValueSerializer(serializer);
      //使用StringRedisSerializer来序列化和反序列化redis的key值
      template.setKeySerializer(new StringRedisSerializer());
      template.afterPropertiesSet();
      return template;
   }

   @Bean
   public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
      return new RedisCacheManager(
              RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
              this.getRedisCacheConfigurationWithTtl( -1), // 默认策略，未配置的 key 会使用这个
              this.getRedisCacheConfigurationMap() // 指定 key 策略
      );
   }

   private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
      Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
      //SsoCache和BasicDataCache进行过期时间配置
      redisCacheConfigurationMap.put("messagCache", this.getRedisCacheConfigurationWithTtl(30 * 60));

      //自定义设置缓存时间
      redisCacheConfigurationMap.put("userCache", this.getRedisCacheConfigurationWithTtl(60));

      return redisCacheConfigurationMap;
   }

   private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
      Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
      ObjectMapper om = new ObjectMapper();
      om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
      om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
      jackson2JsonRedisSerializer.setObjectMapper(om);
      RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
      redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
              RedisSerializationContext
                      .SerializationPair
                      .fromSerializer(jackson2JsonRedisSerializer)
      ).entryTtl(Duration.ofSeconds(seconds));

      return redisCacheConfiguration;
   }
}
