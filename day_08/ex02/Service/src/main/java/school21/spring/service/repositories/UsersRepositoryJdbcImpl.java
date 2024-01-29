package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component("jdbcBean")
public class UsersRepositoryJdbcImpl implements UsersRepository<User> {
    private final DataSource dataSource;

    @Autowired
    public UsersRepositoryJdbcImpl(@Qualifier("driverManagerDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Object entity) {
        String sql = "INSERT INTO \"Users\" (email, password) values(?, ?)";
        String sqlId = "SELECT id FROM \"Users\" WHERE email = ? AND password = ?";
        if (entity.getClass().equals(User.class)) {
            User user = (User) entity;
            try (Connection connection = dataSource.getConnection()) {
                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql);
                preparedStatement.setString(1, user.getEmail());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement(sqlId);
                preparedStatement.setString(1, user.getEmail());
                preparedStatement.setString(2, user.getPassword());
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    user.setId(resultSet.getLong("id"));
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM \"Users\" WHERE id = ?;";
        Optional<User> result = Optional.empty();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User newUser = new User(resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
                result = Optional.ofNullable(newUser);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM \"Users\"";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User newUser = new User(resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
                users.add(newUser);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return users;
    }

    @Override
    public void update(Object entity) {
        User user = (User) entity;
        String sql = "UPDATE \"Users\" SET email = ?, password = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setLong(3, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM \"Users\" WHERE id = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM \"Users\" WHERE email = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User newUser = new User(resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
                return Optional.ofNullable(newUser);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return Optional.empty();
    }
}
