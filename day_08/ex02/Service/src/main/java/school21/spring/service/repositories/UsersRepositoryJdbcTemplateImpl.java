package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component("jdbcTemplateBean")
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository<User> {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = (resultSet, rowNum) -> {
        return new User(
                resultSet.getLong("id"),
                resultSet.getString("email"),
                resultSet.getString("password"));
    };

    @Autowired
    public UsersRepositoryJdbcTemplateImpl(@Qualifier("hikariDataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Object entity) {
        String sql = "INSERT INTO \"Users\" (email, password) VALUES (?, ?)";
        String sqlId = "SELECT max(id) FROM \"Users\"";
        if (entity.getClass().equals(User.class)) {
            User user = (User) entity;
            int result = this.jdbcTemplate.update(sql, user.getEmail(), user.getPassword());
            if (result == 0) {
                System.out.println("Update wasn't executed");
            }
            Long id = jdbcTemplate.queryForObject(sqlId, Long.class);
            user.setId(id);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM \"Users\" WHERE id = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, userRowMapper);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            System.out.println("User with this id not found");
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM \"Users\"";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    @Override
    public void update(Object entity) {
        User user = (User) entity;
        String sql = "UPDATE \"Users\" SET email = ?, password = ? WHERE id = ?";
        this.jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getId());
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM \"Users\" WHERE id = ?";
        this.jdbcTemplate.update(sql, id);

    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM \"Users\" WHERE email = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new Object[]{email}, new BeanPropertyRowMapper<>(User.class));
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            System.out.println("User with this email not found");
        }
        return Optional.empty();
    }
}

