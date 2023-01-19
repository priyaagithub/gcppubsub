package com.migration.gcp;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.pubsub.v1.PubsubMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GoogleMessageReceiver implements MessageReceiver {
    private Logger log = LoggerFactory.getLogger(GoogleMessageReceiver.class);
    @Override
    public void receiveMessage(PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer) {
        //Handle message in db

        log.info("Message Details....");
        log.info(pubsubMessage.getMessageId());
        log.info(pubsubMessage.getData().toStringUtf8());
        ackReplyConsumer.ack();
    }
}
