package hu.hirschbeckd.topicer.controller

import hu.hirschbeckd.topicer.dto.NewTopicDto
import hu.hirschbeckd.topicer.service.TopicCreatorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RequestMapping("/topic/create")
@RestController
class TopicCreateController
@Autowired
constructor(private val topicCreatorService: TopicCreatorService) {

    @PostMapping
    fun createTopics(@RequestBody newTopics: Collection<NewTopicDto>): Mono<Void> {
        return topicCreatorService.createTopics(newTopics);
    }

    @PostMapping("/one")
    fun createTopic(@RequestBody newTopic: NewTopicDto): Mono<Void> {
        return topicCreatorService.createTopic(newTopic);
    }
}
