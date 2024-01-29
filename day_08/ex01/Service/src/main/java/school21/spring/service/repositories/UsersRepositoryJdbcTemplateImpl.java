package school21.spring.service.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository<User> {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);

    }

    @Override
    public void save(Object entity) {
        String sql = "INSERT INTO \"Users\"(email) values(?)";
        String sqlId = "SELECT max(id) FROM \"Users\"";
        if (entity.getClass().equals(User.class)) {
            User user = (User) entity;
            this.jdbcTemplate.update(sql, user.getEmail());
            Long id = jdbcTemplate.queryForObject(sqlId, Long.class);
            user.setId(id);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM \"Users\" WHERE id = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            System.out.println("User with this id not found");
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM \"Users\"";
        List<User> users = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User newUser = new User();
                newUser.setId(resultSet.getLong("id"));
                newUser.setEmail(resultSet.getString("email"));
                return newUser;
            }
        });
        return users;
    }

    @Override
    public void update(Object entity) {
        User user = (User) entity;
        String sql = "UPDATE \"Users\" SET email = ? WHERE id = ?";
        this.jdbcTemplate.update(sql, user.getEmail(), user.getId());
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

