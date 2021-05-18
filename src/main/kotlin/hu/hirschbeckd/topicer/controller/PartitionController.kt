package hu.hirschbeckd.topicer.controller

import hu.hirschbeckd.topicer.service.PartitionService
import io.swagger.annotations.ApiResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/topic/{topicName}/partition")
class PartitionController constructor(
    val partitionService: PartitionService
) {

    @PostMapping("/add")
    fun addPartition(@PathVariable topicName: String): Mono<Mono<Void>> {
        return partitionService.addPartition(topicName);
    }
}
