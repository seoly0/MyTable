package me.seoly.mytable.config

import me.seoly.spring.utils.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class UtilsConfig {

    @Bean
    fun modelMapper(): ModelMapper {
        val mapper = ModelMapper()
        mapper.configuration.matchingStrategy = MatchingStrategies.STRICT
        mapper.configuration.isSkipNullEnabled = true
        mapper.configuration.isAmbiguityIgnored = true
        mapper.configuration.isFullTypeMatchingRequired = false
        mapper.configuration.isDeepCopyEnabled = true
        return mapper
    }
}