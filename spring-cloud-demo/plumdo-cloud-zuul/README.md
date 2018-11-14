# plumdo-cloud-zuul


```
服务网关
可以给外部系统访问Spring-cloud做一个统一管控，例如授权验证等等，内部的互相调用就可以不会收到影响

2种配置：
针对某个特定url地址
zuul.routes.api-a-url.path=/api-a-url/**
zuul.routes.api-a-url.url=http://localhost:2222/

针对serviceId（常用）
zuul.routes.api-a.path=/api-a/**
zuul.routes.api-a.serviceId=compute-service

启动后
测试的url
http://localhost:5555/api-a/from?accessToken=ss
```

