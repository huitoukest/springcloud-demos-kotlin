package tingfeng.demos.springcloud.eureka

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

fun main(args: Array<String>){
    SpringApplication.run(EurekaApplication::class.java, *args)
    /*SpringApplicationBuilder(EurekaApplication::class.java)
            .web(true).
            run(*args)*/
}

@SpringBootApplication
@EnableDiscoveryClient
open class EurekaApplication {

}