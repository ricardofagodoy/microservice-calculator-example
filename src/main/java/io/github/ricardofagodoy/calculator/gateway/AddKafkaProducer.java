package io.github.ricardofagodoy.calculator.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ricardofagodoy.calculator.gateway.dto.IntegerPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AddKafkaProducer implements AddGateway {

    private static final Logger log = LoggerFactory.getLogger(AddKafkaProducer.class);

    private final String topic;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public AddKafkaProducer(@Value("${kafka.add.topic}") String topic,
                            ObjectMapper objectMapper,
                            KafkaTemplate<String, String> kafkaTemplate) {
        this.topic = topic;
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(IntegerPair integers) {

        String jsonObject;

        try {
            jsonObject = this.objectMapper.writeValueAsString(integers);
        } catch (JsonProcessingException e) {
            log.error("Invalid integers format: " + integers, e);
            throw new RuntimeException(e);
        }

        log.info("Sending {} to kafka topic {}", jsonObject, this.topic);
        this.kafkaTemplate.send(this.topic, jsonObject);
    }
}