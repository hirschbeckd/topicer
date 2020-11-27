package hu.hirschbeckd.topicer.controller

import hu.hirschbeckd.topicer.dto.TopicInfoDto
import hu.hirschbeckd.topicer.service.TopicListService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RequestMapping("/topic/list")
@RestController
class TopicListController
@Autowired
constructor(private val topicListService: TopicListService) {

    @GetMapping("/all")
    fun listAll(): Flux<TopicInfoDto> {
        return topicListService.listAllTopics();
    }
}
