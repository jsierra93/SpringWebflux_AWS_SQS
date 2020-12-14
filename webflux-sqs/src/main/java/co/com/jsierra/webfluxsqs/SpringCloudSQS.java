package co.com.jsierra.webfluxsqs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@Lazy
@Slf4j
public class SpringCloudSQS {

    @Value("${sqs.queue-name}")
    private String queueName;

    @Autowired
    QueueMessagingTemplate queueMessagingTemplate;

    /*
    TODO: Obtener el MessageID del mensaje enviado
     */

    public Mono<String> send(String message) {
        UUID uuid = UUID.randomUUID();
        StringBuilder msg = new StringBuilder();
        msg.append(uuid);
        msg.append(" ");
        msg.append(message);
        queueMessagingTemplate.convertAndSend(queueName, msg);
        return Mono.just(msg.toString());
    }

    @Async
    @SqsListener(value = "${sqs.queue-name}", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void queueListener(@Payload String message, Acknowledgment ack, @Header("SenderId") String senderId, @Header("MessageId") String messageID) {
        ack.acknowledge();        //Para marcar como leido el mensaje
        log.info("Received => SenderID: {}, MessageID {}, Message {}", senderId, messageID, message);
    }

}