package hu.hirschbeckd.topicer.service

import hu.hirschbeckd.topicer.dto.NewPartitionDto
import hu.hirschbeckd.topicer.util.KafkaFutureUtil
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.NewPartitions
import org.apache.kafka.clients.admin.TopicDescription
import org.apache.kafka.common.KafkaFuture
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class PartitionService constructor(
    val adminClient: AdminClient
) {

    fun addPartition(topicName: String, partition: NewPartitionDto): Mono<Mono<Void>> {
        val describeTopics = adminClient.describeTopics(listOf(topicName))
        val topicDecriptions: Map<String, KafkaFuture<TopicDescription>> = describeTopics.values()
        val toFlux = KafkaFutureUtil.toFlux(topicDecriptions.values)
        return toFlux.single()
            .map {
                val replicasMatrix = getReplicasMatrix(it, partition)
                val increaseTo = NewPartitions.increaseTo(replicasMatrix.size, listOf(replicasMatrix))
                val createPartitionsResult = adminClient.createPartitions(mapOf(Pair(topicName, increaseTo)))
                KafkaFutureUtil.toMono(createPartitionsResult.all());
            }
    }

    private fun getReplicasMatrix(topicDesc: TopicDescription, partition: NewPartitionDto): List<Int> {
        val replicas = 0..(topicDesc.partitions().size - 1) + partition.replicaCount
        return replicas.toList()
    }
}
