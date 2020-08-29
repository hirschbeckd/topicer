package hu.hirschbeckd.topicer.dto

data class PartitionDto(
        val partitionLeader: String,
        val replicas: Int
)
