package hu.hirschbeckd.topicer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class TopicerApplication

fun main(args: Array<String>) {
    runApplication<TopicerApplication>(*args)
}
