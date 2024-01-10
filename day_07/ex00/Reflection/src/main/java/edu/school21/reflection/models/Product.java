package edu.school21.reflection.models;

import java.util.StringJoiner;

public class Product {
    private int id;
    private String name;
    private Double price;

    public Product() {
        this.id = 0;
        this.name = "product";
        this.price = 0.0;
    }

    public Product(int id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    double countTotalPrice(int amount) {
        return this.price * amount;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("price=" + price)
                .toString();
    }
}
