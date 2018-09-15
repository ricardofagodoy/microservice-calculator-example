package io.github.ricardofagodoy.calculator.ws;

import io.github.ricardofagodoy.calculator.gateway.AddGateway;
import io.github.ricardofagodoy.calculator.gateway.dto.IntegerPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.ACCEPTED;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Path("/add")
public class AddRS {

    private static final Logger log = LoggerFactory.getLogger(AddRS.class);

    private final AddGateway addGateway;

    public AddRS(AddGateway addGateway) {
        this.addGateway = addGateway;
    }

    @GET
    @Path("/{a}/{b}")
    public Response add(@PathParam("a") String a, @PathParam("b") String b) {

        log.info("Received add for {} and {}", a, b);

        try {
            IntegerPair integerPair = new IntegerPair(Integer.parseInt(a), Integer.parseInt(b));
            this.addGateway.send(integerPair);
        } catch (Exception e) {
            return Response.status(BAD_REQUEST).build();
        }

        return Response.status(ACCEPTED).build();
    }
}