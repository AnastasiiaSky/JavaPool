package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsRepositoryJdbcImplTest {
    private DataSource dataSource;
    ProductsRepositoryJdbcImpl productsRepositoryJdbc;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = new ArrayList<>(Arrays.asList(
            new Product(1L, "Milk", 89.90),
            new Product(2L, "Bread", 48.90),
            new Product(3L, "Chocolate", 110.90),
            new Product(4L, "Cookie", 156.90),
            new Product(5L, "Rice", 75.90)));

    final Product EXPECTED_FIND_BY_ID_PRODUCT1 = EXPECTED_FIND_ALL_PRODUCTS.get(0);
    final Product EXPECTED_FIND_BY_ID_PRODUCT2 = EXPECTED_FIND_ALL_PRODUCTS.get(2);
    final Product EXPECTED_FIND_BY_ID_PRODUCT3 = EXPECTED_FIND_ALL_PRODUCTS.get(4);
    final Product EXPECTED_UPDATED_PRODUCT1 = new Product(2L, "Banana", 99.0);
    final Product EXPECTED_UPDATED_PRODUCT2 = new Product(4L, "Pen", 49.50);


    @BeforeEach
    public void init() {
        this.dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("./schema.sql")
                .addScript("./data.sql")
                .build();
        this.productsRepositoryJdbc = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    public void findAllTest() {
        List<Product> methodResult = productsRepositoryJdbc.findAll();
        assertAll("Сравниваем EXPECTED_FIND_ALL_PRODUCTS c результатом работы метода",
                () -> assertFalse(methodResult.isEmpty()),
                () -> assertEquals(EXPECTED_FIND_ALL_PRODUCTS.size(), methodResult.size()),
                () -> assertIterableEquals(EXPECTED_FIND_ALL_PRODUCTS, methodResult),
                () -> assertTrue(methodResult.get(1).equals(EXPECTED_FIND_ALL_PRODUCTS.get(1))),
                () -> assertTrue(EXPECTED_FIND_ALL_PRODUCTS.get(1).toString().equals(methodResult.get(1).toString()))
        );
    }

    @Test
    public void findByIdTest() {
        Optional<Product> methodResultProduct1 = productsRepositoryJdbc.findById(1L);
        Optional<Product> methodResultProduct2 = productsRepositoryJdbc.findById(3L);
        Optional<Product> methodResultProduct3 = productsRepositoryJdbc.findById(5L);


        if (methodResultProduct1.isPresent() && methodResultProduct2.isPresent()
                && methodResultProduct3.isPresent()) {
            Product product1 = methodResultProduct1.get();
            Product product2 = methodResultProduct2.get();
            Product product3 = methodResultProduct3.get();
            assertAll("Сравниваем EXPECTED_FIND_BY_ID_PRODUCT c результатом работы метода",
                    () -> assertEquals(EXPECTED_FIND_BY_ID_PRODUCT1, product1),
                    () -> assertTrue(product1.equals(EXPECTED_FIND_BY_ID_PRODUCT1)),
                    () -> assertTrue(product1.toString().equals(EXPECTED_FIND_BY_ID_PRODUCT1.toString())),
                    () -> assertEquals(EXPECTED_FIND_BY_ID_PRODUCT2, product2),
                    () -> assertTrue(product2.equals(EXPECTED_FIND_BY_ID_PRODUCT2)),
                    () -> assertTrue(product2.toString().equals(EXPECTED_FIND_BY_ID_PRODUCT2.toString())),
                    () -> assertEquals(EXPECTED_FIND_BY_ID_PRODUCT3, product3),
                    () -> assertTrue(product3.equals(EXPECTED_FIND_BY_ID_PRODUCT3)),
                    () -> assertTrue(product3.toString().equals(EXPECTED_FIND_BY_ID_PRODUCT3.toString()))
            );
        }
    }

    @ParameterizedTest
    @ValueSource(longs = {7, 10})
    void findByIdTestNULL(long argument) {
        Optional<Product> expectedNull = Optional.empty();
        Optional<Product> methodResultProduct = productsRepositoryJdbc.findById(argument);
        assertEquals(expectedNull, methodResultProduct);
    }

    @Test
    void updateProductTest() {
        productsRepositoryJdbc.update(new Product(2L, "Banana", 99.0));
        productsRepositoryJdbc.update(new Product(4L, "Pen", 49.50));
        Product updatedProduct1 = productsRepositoryJdbc.findById(2L).get();
        Product updatedProduct2 = productsRepositoryJdbc.findById(4L).get();
        assertEquals(EXPECTED_UPDATED_PRODUCT1, updatedProduct1);
        assertEquals(EXPECTED_UPDATED_PRODUCT2, updatedProduct2);
    }

    @Test
    void saveProductTest() {
        Product product = new Product(6L, "IceCream", 45.50);
        Product product1 = new Product(7L, "Ring", 45000.00);

        long oldMaxId = productsRepositoryJdbc.getMaxId();
        productsRepositoryJdbc.save(product);
        long secondMaxId = productsRepositoryJdbc.getMaxId();
        productsRepositoryJdbc.save(product1);
        long otherMaxId = productsRepositoryJdbc.getMaxId();

        assertEquals(oldMaxId, secondMaxId - 1);
        assertEquals(oldMaxId, otherMaxId - 2);

        assertEquals(product, productsRepositoryJdbc.findById(secondMaxId).get());
        assertEquals(product1, productsRepositoryJdbc.findById(otherMaxId).get());
    }

    @Test
    void deleteProductTest() {
        List<Product> beforeDelete = productsRepositoryJdbc.findAll();
        long id = 5;
        productsRepositoryJdbc.delete(id);
        List<Product> afterDelete = productsRepositoryJdbc.findAll();
        assertFalse(beforeDelete.size() == afterDelete.size());
        assertNotEquals(beforeDelete, afterDelete);

        Optional<Product> expectedNull = Optional.empty();
        Optional<Product> methodResultProduct = productsRepositoryJdbc.findById(id);
        assertEquals(expectedNull, methodResultProduct);
    }
}
