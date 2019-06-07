package com.pantasoft.resource;

import com.pantasoft.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ProducerResource {

    private static Logger LOG = LoggerFactory.getLogger(ProducerResource.class);

    @Autowired
    private KafkaProducer producer;

    @PostMapping("/pub")
    public ResponseEntity sendMessage(@Valid @RequestBody MessageModel messageModel) {

        LOG.info("Message received: {}", messageModel);

        producer.send(messageModel);

        return ResponseEntity.ok().build();
    }

}
