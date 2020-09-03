# springboot-cache
☆springboot,Mybatis-plus,Redis cacheable 


#springboot 学习
☆使用了Springboot  mybatis-plus  redis缓存

☆需要理解 CacheAble 中的unless 指的是条件不成立的时候放入缓存， 也就是条件为false的时候放入缓存

☆需要理解注解 
 
 读取缓存
 
 @Cacheable(cacheNames = {"emp"})
 
 更新缓存
 
 @CachePut(value = "#employee",key = "#employee.id", unless = "!#result")
 
 清除缓存
 
 @CacheEvict(beforeInvocation = true )
