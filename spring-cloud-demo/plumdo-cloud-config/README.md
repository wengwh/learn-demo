# plumdo-cloud-config


```
配置中心
可以把配置文件统一管理

2种配置：
针对GIT配置
#spring.cloud.config.server.git.uri=http://git.oschina.net/didispace/SpringBoot-Learning/
#spring.cloud.config.server.git.searchPaths=Chapter9-1-4/config-repo
#spring.cloud.config.server.git.username=username
#spring.cloud.config.server.git.password=password

针对本地配置
spring.profiles.active=native
#spring.cloud.config.server.native.searchLocations=file:F:/properties/

测试：
添加了compute-service.properties给plumdo-cloud-serviceA读取做测试

```

