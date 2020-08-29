package hu.hirschbeckd.topicer.service

import hu.hirschbeckd.topicer.dto.TopicDto
import hu.hirschbeckd.topicer.util.KafkaFutureUtil
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.TopicDescription
import org.apache.kafka.common.KafkaFuture
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux


@Service
class TopicListService
@Autowired
constructor(
        private val adminClient: AdminClient
) {

    fun listAllTopics(): Flux<TopicDto> {

        val topics = adminClient.listTopics().listings()

        return KafkaFutureUtil.toFlux(topics)
                .concatMap {
                    val describeTopics = adminClient.describeTopics(listOf(it.name()))
                    val topicDecriptions: Map<String, KafkaFuture<TopicDescription>> = describeTopics.values()
                    val toFlux = KafkaFutureUtil.toFlux(topicDecriptions.values)
                    toFlux.map { it ->
                        TopicDto(it.name(), it.partitions().size, null)
                    }
                };

    }

}
