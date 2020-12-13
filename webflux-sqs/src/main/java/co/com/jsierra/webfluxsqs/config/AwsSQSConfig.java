package co.com.jsierra.webfluxsqs.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AwsSQSConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(AwsSQSConfig.class);

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync) {
        return new QueueMessagingTemplate(amazonSQSAsync);
    }

    @Primary
    @Bean
    public AmazonSQSAsync amazonSQSAsync(){
        AWSCredentials credentials = new BasicAWSCredentials("","");
        return AmazonSQSAsyncClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
              //  .withRegion(Regions.US_EAST_1)
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:9324", "us-east-1"))
                .build();
    }
}
