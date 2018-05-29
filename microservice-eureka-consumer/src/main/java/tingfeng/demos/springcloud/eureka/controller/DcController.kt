package tingfeng.demos.springcloud.eureka.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate




@RestController
class DcController {

    @Autowired
    lateinit var loadBalancerClient :LoadBalancerClient
    @Autowired
    lateinit var restTemplate: RestTemplate

    @GetMapping("/consumer")
    fun decconsumer(): String {
        val serviceInstance = loadBalancerClient.choose("eureka-client")
        val url = "http://${serviceInstance.getHost()}:${serviceInstance.getPort()}";

        val result = restTemplate.postForEntity("$url/login",
                mapOf("username" to "user","password" to "password123"), String::class.java)
        print(result)
        val jessesionId = result.toString().replace(Regex(""".*JSESSIONID=([^;]*);.*"""),"$1")

        val headers = HttpHeaders()
        val cookies = ArrayList<String>()
        cookies.add("JSESSIONID=$jessesionId")

        val request = HttpEntity(null, headers)
        return restTemplate.getForObject("$url/dc?JSESSIONID=$jessesionId;", String::class.java)
    }
}