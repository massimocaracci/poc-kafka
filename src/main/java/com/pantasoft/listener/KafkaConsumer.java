package com.pantasoft.listener;

import com.pantasoft.resource.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "panta_topic", groupId = "group")
    public void consume(MessageModel message) {

        LOG.info("Message consumed: {}", message);
    }
}
