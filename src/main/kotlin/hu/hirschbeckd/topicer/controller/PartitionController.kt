package hu.hirschbeckd.topicer.controller

import hu.hirschbeckd.topicer.dto.NewPartitionDto
import hu.hirschbeckd.topicer.service.PartitionService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/topic/{topicName}/partition")
class PartitionController constructor(
    val partitionService: PartitionService
) {

    @PostMapping("/add")
    fun addPartition(@PathVariable topicName: String, @RequestBody newPartitionDto: NewPartitionDto): Mono<Mono<Void>> {
        return partitionService.addPartition(topicName, newPartitionDto);
    }
}
