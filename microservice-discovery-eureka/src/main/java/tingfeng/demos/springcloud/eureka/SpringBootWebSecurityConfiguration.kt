package tingfeng.demos.springcloud.eureka

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order


class SpringBootWebSecurityConfiguration {

    @Configuration
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    internal class DefaultConfigurerAdapter : WebSecurityConfigurerAdapter() {

        @Throws(Exception::class)
        override fun configure(http: HttpSecurity) {
            super.configure(http)//新包这两行被删了
            http.csrf().disable()
        }
    }
}