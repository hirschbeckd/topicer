package hu.hirschbeckd.topicer.service

import org.apache.kafka.clients.admin.AdminClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TopicListService
@Autowired
constructor(
        val adminClient: AdminClient
){

    fun listAllTopics() {
        val topics = adminClient.listTopics();
    }

}
