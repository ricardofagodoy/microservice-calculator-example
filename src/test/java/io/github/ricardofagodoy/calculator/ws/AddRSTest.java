package io.github.ricardofagodoy.calculator.ws;

import io.github.ricardofagodoy.calculator.gateway.AddGateway;
import org.junit.Before;
import org.junit.Test;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.ACCEPTED;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
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

        Response response = addRS.add("1", "2");

        assertEquals(ACCEPTED.getStatusCode(), response.getStatus());
    }

    @Test
    public void givenInvalidIntegerAThenBadRequestMustBeReturned() {

        Response response = addRS.add("a", "2");

        assertEquals(BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void givenInvalidIntegerBThenBadRequestMustBeReturned() {

        Response response = addRS.add("1", "b");

        assertEquals(BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void givenEmptyValuesThenBadRequestMustBeReturned() {

        Response response = addRS.add("", "");

        assertEquals(BAD_REQUEST.getStatusCode(), response.getStatus());
    }
}