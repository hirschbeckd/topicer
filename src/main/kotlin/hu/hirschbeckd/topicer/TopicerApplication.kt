package hu.hirschbeckd.topicer

import hu.hirschbeckd.topicer.config.properties.KafkaProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication


@SpringBootApplication
@EnableConfigurationProperties(KafkaProperties::class)
class TopicerApplication

fun main(args: Array<String>) {
    runApplication<TopicerApplication>(*args)
}
