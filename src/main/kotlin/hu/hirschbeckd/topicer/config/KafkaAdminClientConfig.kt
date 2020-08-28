package hu.hirschbeckd.topicer.config

import hu.hirschbeckd.topicer.config.properties.KafkaProperties
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.AdminClientConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class KafkaAdminClientConfig {

    @Autowired
    lateinit var kafkaProperties: KafkaProperties;

    @Bean
    fun adminClient(): AdminClient {
        val properties = getKafkaProperties()
        return AdminClient.create(properties)
    }

    private fun getKafkaProperties(): Properties {
        val properties = Properties()
        properties[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaProperties.serverUrls
        return properties
    }
}
