package io.github.ricardofagodoy.calculator.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ricardofagodoy.calculator.gateway.dto.IntegerPair;
import org.junit.Before;
import org.junit.Test;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddKafkaProducerTest {

    private AddKafkaProducer addKafkaProducer;
    private KafkaTemplate kafkaTemplate;
    private ObjectMapper objectMapper;
    private static final String TOPIC = "TOPIC";

    @Before
    public void init() {
        this.kafkaTemplate = mock(KafkaTemplate.class);
        this.objectMapper = mock(ObjectMapper.class);
        this.addKafkaProducer = new AddKafkaProducer(TOPIC, objectMapper, kafkaTemplate);
    }

    @Test(expected = RuntimeException.class)
    public void givenInvalidIntegerPairObjectThenExceptionMustBeThrown() throws JsonProcessingException {

        IntegerPair integerPair = mock(IntegerPair.class);

        when(this.objectMapper.writeValueAsString(integerPair)).thenThrow(new Exception());

        this.addKafkaProducer.send(integerPair);
    }

    @Test
    public void givenIntegerPairObjectThenTemplateMustBeCalled() throws JsonProcessingException {

        String payload = "payload";
        IntegerPair integerPair = mock(IntegerPair.class);

        when(this.objectMapper.writeValueAsString(integerPair)).thenReturn(payload);

        this.addKafkaProducer.send(integerPair);

        verify(this.kafkaTemplate).send(TOPIC, payload);
    }
}