package school21.spring.service.application;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.springframework.jdbc.core.JdbcTemplate;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.Set;


public class DataBaseInitializing {


    private static final String PACKAGE = "school21.spring.service.models";
    private JdbcTemplate jdbcTemplate;

    public DataBaseInitializing(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void initDataBase() {
        this.jdbcTemplate.execute(String.format("DROP TABLE IF EXISTS \"Users\"  CASCADE"));
        StringBuilder columns = getColumnsForTheTable(User.class);
        String query = String.format("CREATE TABLE \"Users\" (id bigserial primary key, %s)", columns);
        this.jdbcTemplate.execute(query);
    }

    private StringBuilder getColumnsForTheTable(Class<?> clazz) {
        StringBuilder columns = new StringBuilder();
        for (Field field : clazz.getDeclaredFields()) {
            if (!field.getName().equals("id")) {
                columns.append(field.getName()).append(" text not null, ");
            }
        }
        columns.deleteCharAt(columns.lastIndexOf(", "));
        return columns;
    }
}







