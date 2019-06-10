package com.pantasoft;

import com.github.javafaker.Faker;
import com.pantasoft.producer.KafkaProducer;
import com.pantasoft.resource.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(Application.class);

    @Autowired
    KafkaProducer kafkaProducer;

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        LOG.debug("Command line starting");

        Runnable myRun = () -> {

            var fake = new Faker();

            var message = new MessageModel();
            message.setName(fake.name().fullName());
            message.setCountry(fake.address().country());

            kafkaProducer.send(
                    message
            );

        };

        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(myRun, 1, 1, TimeUnit.SECONDS);
    }
}