package me.seoly.mytable.config

import jakarta.servlet.Filter
import me.seoly.mytable.filter.RequestLoggingFilter
import me.seoly.mytable.filter.RequestLoggingFilterOption
import me.seoly.spring.filter.ReReadableRequestBody
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.CommonsRequestLoggingFilter
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.UUID

@Configuration
class FilterConfig {

    @Bean
    fun reReadableRequestBodyFilter(): FilterRegistrationBean<Filter> {
        val bean = FilterRegistrationBean<Filter>()

        bean.filter = ReReadableRequestBody()
        bean.order = 1
        bean.addUrlPatterns("/v1/user/*")

        return bean
    }

    @Bean
    fun requestLoggingFilter(): FilterRegistrationBean<Filter> {
        val bean = FilterRegistrationBean<Filter>()

        bean.filter = RequestLoggingFilter(object: RequestLoggingFilterOption {
            override val sampleRate: Double = 1.0
        }) { request, log ->
            val headers = request.headerNames.toList().joinToString { "${it}: ${request.getHeader(it)}" }
            val body = StringBuilder()
            val reader = BufferedReader(InputStreamReader(request.inputStream, StandardCharsets.UTF_8))
            var line: String?
            while ((reader.readLine().also { line = it }) != null) {
                body.append(line)
            }

            log.info("Headers\t:: $headers")
            log.info("Query\t\t:: ${request.queryString}")
            if (body.isNotBlank())
                log.info("Body\t\t:: $body")
        }
        bean.order = 2

        return bean
    }
}