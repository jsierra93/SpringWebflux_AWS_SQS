package co.com.jsierra.webfluxsqs.config;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSQSConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(AwsSQSConfig.class);

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync) {
        return new QueueMessagingTemplate(amazonSQSAsync);
    }
}
