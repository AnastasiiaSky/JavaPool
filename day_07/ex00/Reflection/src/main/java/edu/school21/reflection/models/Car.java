package edu.school21.reflection.models;

import java.util.StringJoiner;

public class Car {
    private String model;
    private String color;
    private Double price;

    public Car() {
        this.model = "Polo";
        this.color = "red";
        this.price = 1000000.0;
    }

    public Car(String model, String color, Double price) {
        this.model = model;
        this.color = color;
        this.price = price;
    }

    public void move() {
        String start = "Start the engine.... Wooom.. Wooom.. Wooom..";
        System.out.printf("I'm %s %s\nMy price is %f\nLet's go!\n%s", color, model, price, start);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Car.class.getSimpleName() + "[", "]")
                .add("model='" + model + "'")
                .add("color='" + color + "'")
                .add("price=" + price)
                .toString();
    }
}
