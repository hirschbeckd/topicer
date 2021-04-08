package hu.hirschbeckd.topicer.service

import hu.hirschbeckd.topicer.dto.NewPartitionDto
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.NewPartitions
import org.springframework.stereotype.Service

@Service
class PartitionService constructor(
    val adminClient: AdminClient
) {

    fun addPartition(topicName: String, partition: NewPartitionDto) {
        val replicas = 0..partition.replicaCount + 1
        val replicasMatrix = listOf(replicas.toList())
        val increaseTo = NewPartitions.increaseTo(1, replicasMatrix)
        adminClient.createPartitions(mapOf(Pair(topicName, increaseTo)))
    }
}
