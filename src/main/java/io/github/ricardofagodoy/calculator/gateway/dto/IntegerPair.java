package io.github.ricardofagodoy.calculator.gateway.dto;

public class IntegerPair {

    private final Integer a;
    private final Integer b;

    public IntegerPair(Integer a, Integer b) {
        this.a = a;
        this.b = b;
    }

    public Integer getA() {
        return a;
    }

    public Integer getB() {
        return b;
    }

    @Override
    public String toString() {
        return "IntegerPair{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}