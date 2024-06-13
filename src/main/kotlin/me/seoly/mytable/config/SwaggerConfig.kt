package me.seoly.mytable.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License

@Configuration
class SwaggerConfig {

    @Bean
    fun api(): OpenAPI {
        return OpenAPI()
            .info(
                Info().title("Good Table")
                    .description("Seoly Good Table API")
                    .version("v0.0.1")
                    .license(License().name("Apache 2.0").url("http://springdoc.org"))
            )
    }
}