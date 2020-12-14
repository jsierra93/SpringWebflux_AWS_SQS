package co.com.jsierra.webfluxsqs.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AwsSQSConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(AwsSQSConfig.class);

    @Value("${sqs.queue-name}")
    private String queueName;

    @Value("${sqs.region}")
    private String region;

    @Value("${sqs.endpoint}")
    private String endpoint;

    @Value("${sqs.accessKey}")
    private String accessKey;

    @Value("${sqs.secretKey}")
    private String secretKey;


    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync) {
        return new QueueMessagingTemplate(amazonSQSAsync);
    }

    @Primary
    @Bean
    public AmazonSQSAsync amazonSQSAsync(){
        AWSCredentials credentials = new BasicAWSCredentials(accessKey,secretKey);
        return AmazonSQSAsyncClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
                .build();
    }
}
