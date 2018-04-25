
参考：https://www.jianshu.com/p/c52b1089ea92

#微服务的项目变更

## 名称更改

18年4月，目前SpringCloud版本为Finchley.M9
1.从表面上看对Eureka，Feign，hystrix等项目名称做了修改，
Edgware.RELEASE
spring-cloud-starter-eureka-server

spring-cloud-starter-eureka

spring-cloud-starter-hystrix

spring-cloud-starter-hystrix-dashboard

spring-cloud-starter-feign

变为：

Finchley.M9
spring-cloud-starter-netflix-eureka-server

spring-cloud-starter-netflix-eureka-client

spring-cloud-starter-netflix-hystrix

spring-cloud-starter-netflix-hystrix-dashboard

spring-cloud-starter-openfeign

## 组件变更

2.加入了一些新组件，抛弃了一些旧组件
加入Gateway:
加入Reactive Web
加入Reactive Cloud Stream
Quartz Scheduler 任务调度
Statemachine 状态机


抛弃Apache Camel 
它一个规则引擎，可以通过领域语言提供基于规则的路由引擎，
由于Zuul以及新加入的Gateway的存在，被取缔了

抛弃SpringBootAdmin 微服务监控
https://blog.csdn.net/soberchina/article/details/72953994


# 注解变化

3.使用Endpoint注解，
actuator以及bus的调用方式做了修改，
以bus为例，源码有以下改动
Edgware.RELEASE

Finchley.M9

通过上图可以看到接口的定义方式已经发生改变
This allows us to offer support on Servlet-based 
apps (Spring MVC and Jersey) as well as reactive apps

@Endpoint注解，id即接口访问路径。

@ReadOperation可以用GET方式请求 
@WriteOperation可以用POST方式请求

SpringCloud默认的安全配置不会启动Endpoint，
需要添加一下配置才会启动

properties:
management.endpoints.web.exposure.include=*
yml:
management:
endpoints:
web:
exposure:
include: '*'

参考：https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-Security-2.0

SpringBoot2.0对用户定义的endpoint和执行
器的endpoint不提供分离的自动配置，当使
用了Spring Security，自动配置默认保护所有的endpoints。
加入了@EnableWebSecurity注解和
基于Spring Security的内容协商策略的信任，来决
定用httpBasic还是formLogin。加入了一个有默认
用户名和密码的用户，它可以用来登录。

# 参数配置

4.配置参数修改
4.1 management.security.enabled=false配置过时，

4.2 SpringCloud提供了默认的安全配置，如果
使用自定义安全控制，默认配置会失效。

自定义Endpoint安全控制

<dependency>

<groupId>org.springframework.boot</groupId>

<artifactId>spring-boot-starter-security</artifactId>

</dependency>
4.3 继承WebSecurityConfigurerAdapter

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;

import org.springframework.context.annotation.Configuration;

import org.springframework.core.annotation.Order;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration

@Order(1)

public class ActuatorWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

@Override

protected void configure(HttpSecurity http) throws Exception {

http

.authorizeRequests()

.requestMatchers(EndpointRequest.to("env")).permitAll()

.requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ACTUATOR")

.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()

.antMatchers("/**").hasRole("USER")

.and()

.httpBasic();

}

}

#参考资料

Springboot2

https://spring.io/blog/2017/09/15/security-changes-in-spring-boot-2-0-m4

https://spring.io/blog/2017/08/22/introducing-actuator-endpoints-in-spring-boot-2-0

Actuator相关

https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html

https://www.infoq.com/news/2017/08/spring-boot-2-actuator-endpoints

https://github.com/spring-projects/spring-boot/wiki/Migrating-a-custom-Actuator-endpoint-to-Spring-Boot-2

Eureka Clients

https://cloud.spring.io/spring-cloud-netflix/multi/multi__service_discovery_eureka_clients.html

Endpoints

https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html

content negotiation

https://blog.csdn.net/u012410733/article/details/78536656

Reactive Web Applications

https://docs.spring.io/spring/docs/5.0.0.M4/spring-framework-reference/html/web-reactive.html

https://spring.io/blog/2016/06/07/notes-on-reactive-programming-part-i-the-reactive-landscape

事件驱动和消息驱动

http://www.cnblogs.com/welen/articles/5115213.html

作者：元代码
链接：https://www.jianshu.com/p/c52b1089ea92
來源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。