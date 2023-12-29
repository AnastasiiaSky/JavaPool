package edu.school21.models;

import java.util.Objects;
import java.util.StringJoiner;

public class Product {
    private final Long id;
    private final String name;
    private final double price;

    public Product(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        if (this.hashCode() != product.hashCode()) return false;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(55L * id + 33L * name.length() + 44 * (long) price);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "{", "}")
                .add("id=" + id)
                .add("name=" + name + "'")
                .add("price=" + price)
                .toString();
    }
}
