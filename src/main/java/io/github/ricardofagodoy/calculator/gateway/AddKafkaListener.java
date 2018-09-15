package io.github.ricardofagodoy.calculator.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ricardofagodoy.calculator.gateway.dto.IntegerPair;
import io.github.ricardofagodoy.calculator.usecases.AddTwoIntegers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Profile("consumer")
@Component
public class AddKafkaListener {

    private static final Logger log = LoggerFactory.getLogger(AddKafkaListener.class);

    private final ObjectMapper objectMapper;
    private final AddTwoIntegers addTwoIntegers;

    public AddKafkaListener(ObjectMapper objectMapper,
                            AddTwoIntegers addTwoIntegers) {
        this.objectMapper = objectMapper;
        this.addTwoIntegers = addTwoIntegers;
    }

    @KafkaListener(topics = "${kafka.add.topic}")
    public void listenAdd(@Payload String message) {
        log.info("Received message='{}'", message);

        IntegerPair integerPair;

        try {
            integerPair = this.objectMapper.readValue(message, IntegerPair.class);
        } catch (IOException e) {
            log.error("Invalid integers format: " + message, e);
            throw new RuntimeException(e);
        }

        this.addTwoIntegers.add(integerPair.getA(), integerPair.getB());
    }
}