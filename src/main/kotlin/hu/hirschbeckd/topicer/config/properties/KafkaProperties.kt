package hu.hirschbeckd.topicer.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding


@ConstructorBinding
@ConfigurationProperties(prefix = "topical.kafka")
data class KafkaProperties(
        val serverUrls: String
)
