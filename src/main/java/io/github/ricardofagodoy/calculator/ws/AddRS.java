package io.github.ricardofagodoy.calculator.ws;

import io.github.ricardofagodoy.calculator.gateway.AddGateway;
import io.github.ricardofagodoy.calculator.gateway.dto.IntegerPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.websocket.server.PathParam;
import javax.xml.ws.Response;

@RestController
@RequestMapping("/add")
public class AddRS {

    private static final Logger log = LoggerFactory.getLogger(AddRS.class);

    private final AddGateway addGateway;

    public AddRS(AddGateway addGateway) {
        this.addGateway = addGateway;
    }

    @RequestMapping(path = "/{a}/{b}", method = RequestMethod.GET)
    public ResponseEntity add(@PathVariable("a") String a, @PathVariable("b") String b) {

        log.info("Received add for {} and {}", a, b);

        try {
            IntegerPair integerPair = new IntegerPair(Integer.parseInt(a), Integer.parseInt(b));
            this.addGateway.send(integerPair);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.accepted().build();
    }
}