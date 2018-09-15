package io.github.ricardofagodoy.calculator.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.ricardofagodoy.calculator.gateway.dto.IntegerPair;

public interface AddGateway {
    void send(IntegerPair integers) throws JsonProcessingException;
}