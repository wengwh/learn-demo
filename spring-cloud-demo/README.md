# spring-cloud-demo


```
spring-cloud的demo项目：

plumdo-cloud-register：服务注册中心，端口：1111
plumdo-cloud-config：配置中心，端口：7001
plumdo-cloud-service：服务提供者，端口：2222，2111
plumdo-cloud-consumer：服务消费者，端口：3333
plumdo-cloud-zuul：服务网关，端口：5555

```


```
启动顺序：
plumdo-cloud-register
plumdo-cloud-config
plumdo-cloud-service
plumdo-cloud-consumer
plumdo-cloud-zuul

```

```
测试访问地址：

访问注册中心：
http://localhost:1111/

访问服务网关：
http://localhost:5555/api-a/from?accessToken=ss
http://localhost:5555/api-a-url/from?accessToken=ss

访问消费者：
http://localhost:3333/users
http://localhost:3333/users-feign

访问提供者：
http://localhost:2111/from
http://localhost:2222/from

```

```
后续：
配置中心，注册中心还是单点配置，加入分布式配置。
配置中心现在只做了一个测试，把全部配置统一管理。
```
