package com.pantasoft;

import com.pantasoft.resource.MessageModel;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    /**
     * Producer config
     **/
    @Bean
    public NewTopic topic1() {

        return new NewTopic("panta_topic", 10, (short) 2);
    }

    @Bean
    public ProducerFactory<String, MessageModel> producerFactory() {
        return new DefaultKafkaProducerFactory<>(Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class
        ));
    }

    @Bean
    public KafkaTemplate<String, MessageModel> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    /**
     * Consumer config
     **/
    @Bean
    public ConsumerFactory pantaConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                Map.of(
                        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                        ConsumerConfig.GROUP_ID_CONFIG, "group",
                        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class,
                        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class),
                new StringDeserializer(),
                new JsonDeserializer<>(MessageModel.class)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessageModel> concurrentKafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, MessageModel> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(pantaConsumerFactory());
        return factory;
    }
}
