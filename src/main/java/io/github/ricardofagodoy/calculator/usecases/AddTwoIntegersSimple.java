package io.github.ricardofagodoy.calculator.usecases;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AddTwoIntegersSimple implements AddTwoIntegers {

    private static final Logger log = LoggerFactory.getLogger(AddTwoIntegersSimple.class);

    @Override
    @Async
    public void add(final Integer a, final Integer b) {

        Integer result = Integer.sum(a, b);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("Result of add to {} and {} is {}", a, b, result);
    }
}