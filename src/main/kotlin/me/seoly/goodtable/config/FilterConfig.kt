package me.seoly.goodtable.config

import jakarta.servlet.Filter
import me.seoly.spring.filter.ReReadableRequestBody
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig {

    @Bean
    fun reReadableRequestBodyFilter(): FilterRegistrationBean<Filter> {
        val bean = FilterRegistrationBean<Filter>();

        bean.filter = ReReadableRequestBody()
        bean.order = 1
        bean.addUrlPatterns("/v1/user/*")

        return bean
    }
}