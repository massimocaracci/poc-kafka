package com.pantasoft.producer;

import com.pantasoft.resource.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private static Logger LOG = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, MessageModel> kafkaTemplate;

    public void send(MessageModel value) {

        kafkaTemplate.send(
                "panta_topic",
                value
        );

        LOG.info("Message sent to {}", value);
    }
}
