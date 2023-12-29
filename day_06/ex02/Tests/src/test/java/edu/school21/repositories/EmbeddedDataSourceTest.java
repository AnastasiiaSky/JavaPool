package edu.school21.repositories;


import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmbeddedDataSourceTest {
    private DataSource dataSource = null;

    @BeforeEach
    void init() {
        this.dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("./schema.sql")
                .addScript("./data.sql")
                .build();
    }

    @Test
    void testConnection() {
        try (Connection connection = dataSource.getConnection()) {
            assertNotNull(dataSource.getConnection());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

}

