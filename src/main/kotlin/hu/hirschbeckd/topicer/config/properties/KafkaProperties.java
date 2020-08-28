package hu.hirschbeckd.topicer.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "topical.kafka")
public class KafkaProperties {

    private String serverUrls;
}
