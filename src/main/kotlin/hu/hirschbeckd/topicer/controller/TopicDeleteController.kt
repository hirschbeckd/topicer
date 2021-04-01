package hu.hirschbeckd.topicer.controller

import hu.hirschbeckd.topicer.service.TopicDeleteService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/topic/delete")
class TopicDeleteController constructor(
    private val topicDeleteService: TopicDeleteService
) {

    @PostMapping
    fun deleteTopics(@RequestBody topicNames: Collection<String>): Mono<Void> {
        return topicDeleteService.deleteTopics(topicNames);
    }

    @PostMapping("/one")
    fun deleteTopic(@RequestBody topicName: String): Mono<Void> {
        return topicDeleteService.deleteTopic(topicName);
    }
}
