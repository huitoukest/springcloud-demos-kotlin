package tingfeng.demos.springcloud.eureka

fun main(args: Array<String>){
    SpringApplication.run(EurekaApplication::class.java, *args)
}

@SpringBootApplication
@EnableEurekaServer
open class EurekaApplication {

}