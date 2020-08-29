package hu.hirschbeckd.topicer.dto


data class TopicDto(
        val name: String,
        val numberOfPartitions: Int,
        val partitions: List<PartitionDto>?
) {
}
