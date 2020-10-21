# springboot-cache  和 多线程应用相关的一些例子
☆springboot,Mybatis-plus,Redis cacheable,Thread


###springboot 学习
☆使用了Springboot  mybatis-plus  redis缓存

☆需要理解 CacheAble 中的unless 指的是条件不成立的时候放入缓存， 也就是条件为false的时候放入缓存

☆需要理解注解 

类注解

```java
@CacheConfig(cacheNames = "emp")
```



 读取缓存

 @Cacheable( key = "#id")

 更新缓存

 @CachePut(key = "#employee.id")

 清除缓存

 @CacheEvict(beforeInvocation = true )
