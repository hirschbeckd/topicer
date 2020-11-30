package hu.hirschbeckd.topicer.service

import hu.hirschbeckd.topicer.dto.NewTopicDto
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicCreatorService constructor(
        private val adminClient: AdminClient
) {

    fun createTopics(newTopics: Collection<NewTopicDto>) {

        val newKafkaTopics = newTopics.stream()
                .map { NewTopic(it.name, it.numberOfPartitions, it.replicationFactor) }
                .collect(Collectors.toList())

        adminClient.createTopics(newKafkaTopics)
    }

    fun createTopic(newTopic: NewTopicDto) {
        createTopics(listOf(newTopic))
    }

}
