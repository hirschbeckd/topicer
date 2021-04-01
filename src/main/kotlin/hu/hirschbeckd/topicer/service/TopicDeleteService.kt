package hu.hirschbeckd.topicer.service

import hu.hirschbeckd.topicer.util.KafkaFutureUtil
import org.apache.kafka.clients.admin.AdminClient
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class TopicDeleteService constructor(
    private val adminClient: AdminClient
) {

    fun deleteTopics(topicNames: Collection<String>): Mono<Void> {
        return KafkaFutureUtil.toMono(adminClient.deleteTopics(topicNames).all())
    }

    fun deleteTopic(topicName: String): Mono<Void> {
        return deleteTopics(listOf(topicName))
    }
}
