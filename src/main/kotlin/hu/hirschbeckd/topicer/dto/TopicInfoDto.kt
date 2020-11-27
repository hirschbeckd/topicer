package hu.hirschbeckd.topicer.dto


data class TopicInfoDto(
        val name: String,
        val partitionCount: Int,
        val partitions: List<PartitionDto>?
) {
}
