package io.github.ricardofagodoy.calculator.ws;

import io.github.ricardofagodoy.calculator.gateway.AddGateway;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class AddRSTest {

    private AddRS addRS;

    @Before
    public void init() {
        this.addRS = new AddRS(mock(AddGateway.class));
    }

    @Test
    public void givenTwoValidIntegersThenAcceptedMustBeReturned() {

        ResponseEntity response = addRS.add("1", "2");

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    @Test
    public void givenInvalidIntegerAThenBadRequestMustBeReturned() {

        ResponseEntity response = addRS.add("a", "2");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void givenInvalidIntegerBThenBadRequestMustBeReturned() {

        ResponseEntity response = addRS.add("1", "b");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void givenEmptyValuesThenBadRequestMustBeReturned() {

        ResponseEntity response = addRS.add("", "");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}