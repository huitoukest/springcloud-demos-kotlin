package tingfeng.demos.springcloud.eureka

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

fun main(args: Array<String>){
    SpringApplication.run(EurekaApplication::class.java, *args)
    /*SpringApplicationBuilder(EurekaApplication::class.java)
            .web(true).
            run(*args)*/
}

@SpringBootApplication
@EnableDiscoveryClient
open class EurekaApplication {

    @Bean
    open fun restTemplate():RestTemplate{
        return RestTemplate()
    }
}