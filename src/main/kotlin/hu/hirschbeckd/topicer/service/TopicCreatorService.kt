package hu.hirschbeckd.topicer.service

import hu.hirschbeckd.topicer.dto.NewTopicDto
import hu.hirschbeckd.topicer.util.KafkaFutureUtil
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.function.Consumer
import java.util.stream.Collectors

@Service
class TopicCreatorService constructor(
        private val adminClient: AdminClient
) {

    fun createTopics(newTopics: Collection<NewTopicDto>): Mono<Void> {

        val newKafkaTopics = newTopics.stream()
                .map { NewTopic(it.name, it.numberOfPartitions, it.replicationFactor).configs(it.configs) }
                .collect(Collectors.toList())

        val createTopicsResult = adminClient.createTopics(newKafkaTopics)

         return KafkaFutureUtil.toMono(createTopicsResult.all())
    }

    fun createTopic(newTopic: NewTopicDto): Mono<Void> {
        return createTopics(listOf(newTopic))
    }

}
