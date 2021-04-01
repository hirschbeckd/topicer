package hu.hirschbeckd.topicer.dto

class NewTopicDto(
 val name: String,
 val numberOfPartitions: Int,
 val replicationFactor: Short,
 val configs: Map<String, String>
)
