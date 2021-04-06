package hu.hirschbeckd.topicer.service

import hu.hirschbeckd.topicer.dto.PartitionDto
import hu.hirschbeckd.topicer.dto.TopicInfoDto
import hu.hirschbeckd.topicer.util.KafkaFutureUtil
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.TopicDescription
import org.apache.kafka.common.KafkaFuture
import org.apache.kafka.common.TopicPartitionInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.util.stream.Collectors


@Service
class TopicListService
@Autowired
constructor(
        private val adminClient: AdminClient
) {

    fun listAllTopics(): Flux<TopicInfoDto> {

        val topics = adminClient.listTopics().listings()

        return KafkaFutureUtil.toFlux(topics)
                .concatMap {
                    val describeTopics = adminClient.describeTopics(listOf(it.name()))
                    val topicDecriptions: Map<String, KafkaFuture<TopicDescription>> = describeTopics.values()
                    val toFlux = KafkaFutureUtil.toFlux(topicDecriptions.values)
                    toFlux.map { it ->
                        TopicInfoDto(it.name(), it.partitions().size, getPartitions(it))
                    }
                };

    }

    private fun getPartitions(it: TopicDescription): List<PartitionDto> {
        return it.partitions()
                .stream()
                .map { partition ->
                    val leader = partition.leader()
                    PartitionDto(leader?.toString(), partition.replicas().size, getReplicas(partition))
                }
                .collect(Collectors.toList())
    }

    private fun getReplicas(partition: TopicPartitionInfo): List<String> {
        return partition.replicas().stream()
                .map { replica -> replica.toString() }
                .collect(Collectors.toList())
    }

}
