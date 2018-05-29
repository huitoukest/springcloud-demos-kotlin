package tingfeng.demos.springcloud.eureka.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.web.bind.annotation.RestController


@RestController
class DcController {

    @Autowired
    lateinit var discoveryClient: DiscoveryClient

    @GetMapping("/dc")
    fun dc(): String {
        val services = "Services: " + discoveryClient.getServices()
        println(services)
        return services
    }

}