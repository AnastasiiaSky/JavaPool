package edu.school21.ORM.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSourceConfiguration {
    public static HikariDataSource dataSource;
    private static HikariConfig config = new HikariConfig();

    static {
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("postgres");
        config.setPassword("");
        config.setConnectionTimeout(5000);
        dataSource = new HikariDataSource(config);
    }

    public static void closeConnection() {
        dataSource.close();
    }
}

