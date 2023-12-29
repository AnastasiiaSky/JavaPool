package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private final DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() {
        ArrayList<Product> products = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet productResultSet = statement.executeQuery("SELECT * FROM Product;");
            while (productResultSet.next()) {
                Product product = new Product(productResultSet.getLong("id"),
                        productResultSet.getString("name"),
                        productResultSet.getDouble("price"));
                products.add(product);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<Product> productOptional = Optional.empty();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet productResultSet = statement.executeQuery(String.format("SELECT id, name, price FROM Product " +
                    "WHERE id = %d;", id));
            if (productResultSet.next()) {
                productOptional = Optional.of(new Product(productResultSet.getLong("id"),
                        productResultSet.getString("name"),
                        productResultSet.getDouble("price")));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return productOptional;
    }

    @Override
    public void update(Product product) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("UPDATE Product SET name = \'" + product.getName()
                    + "\', price = \'" + product.getPrice() + "\' WHERE id = " + product.getId());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void save(Product product) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO Product(id, name, price)\n" + "VALUES(\'"
                    + product.getId() + "\', \'" + product.getName() + "\', \'" + product.getPrice() + " \')");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM Product WHERE id = \'" + id + "\'");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public long getMaxId() {
        long maxId = 0;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet idResultSet = statement.executeQuery("SELECT MAX(id) FROM Product;");
            if (idResultSet.next()) {
                maxId = idResultSet.getLong(1);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return maxId;
    }
}
