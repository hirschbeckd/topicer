package hu.hirschbeckd.topicer.dto

data class PartitionDto(
        val partitionLeader: String?,
        val replicaCount: Int,
        val replicas: List<String>
)
