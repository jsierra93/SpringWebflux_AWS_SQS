package co.com.jsierra.webfluxsqs;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Lazy
@Slf4j
public class SpringCloudSQS {
   private static final Logger logger = LoggerFactory.getLogger(SpringCloudSQS.class);

    static final String QUEUE_NAME = "webflux-sqs";

    /*
     * CountDownLatch is added to wait for messages
     * during integration test
     */
    CountDownLatch countDownLatch;

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Autowired
    QueueMessagingTemplate queueMessagingTemplate;

    @SqsListener(QUEUE_NAME)
    public void receiveMessage(String message, @Header("SenderId") String senderId) {
        logger.info("Received message: {}  having SenderId: {}", message, senderId);
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    public void send(Object message) {
        queueMessagingTemplate.convertAndSend(QUEUE_NAME, message);
        log.info("Send message: {}", message);
    }


    /*  @SqsListener(value = "https://sqs.us-east-1.amazonaws.com/047156755745/webflux-sqs", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void queueListener(@Payload String log, Acknowledgment ack) {
        LOGGER.info("Se recibe por SQSListener {}", log);
    }*/

}