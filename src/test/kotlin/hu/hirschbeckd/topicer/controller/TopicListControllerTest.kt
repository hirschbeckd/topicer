package hu.hirschbeckd.topicer.controller

import hu.hirschbeckd.topicer.service.TopicListService
import org.apache.kafka.clients.admin.NewTopic
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.annotation.DirtiesContext

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = ["listeners=PLAINTEXT://localhost:9092", "port=9092"])
internal class TopicListControllerTest
@Autowired constructor(val topicListService: TopicListService){



    @TestConfiguration
    internal class TestConfig {

        @Bean
        fun createTestTopic() : NewTopic {
            return NewTopic("test_topic_1", 1, 1);
        }
    }

    @Test
    fun listAll_listTopics() {
    }

}
