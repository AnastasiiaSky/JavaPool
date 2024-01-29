package school21.spring.service.application;

import org.springframework.jdbc.core.JdbcTemplate;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.lang.reflect.Field;

public class DataBaseInitializing {
    private final JdbcTemplate jdbcTemplate;

    public DataBaseInitializing(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void initDataBase() {
        this.jdbcTemplate.execute("DROP TABLE IF EXISTS \"Users\" CASCADE");
        StringBuilder columns = getColumnsForTheTable();
        String query = String.format("CREATE TABLE \"Users\" (id bigserial primary key, %s)", columns);
        this.jdbcTemplate.execute(query);
    }

    private StringBuilder getColumnsForTheTable() {
        StringBuilder columns = new StringBuilder();
        for (Field field : User.class.getDeclaredFields()) {
            if (!field.getName().equals("id")) {
                columns.append(field.getName()).append(" text not null, ");
            }
        }
        columns.deleteCharAt(columns.lastIndexOf(", "));
        return columns;
    }
}
