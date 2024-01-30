package school21.spring.service.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import school21.spring.service.config.TestApplicationConfig;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

public class UsersServiceImplTest {

    TestApplicationConfig testApplicationConfig;
    private DataSource dataSource;
    private UsersRepository usersRepository;
    private UsersServiceImpl usersService;

    @BeforeEach
    public void init() {
        testApplicationConfig = new TestApplicationConfig();
        testApplicationConfig.init();
        dataSource = testApplicationConfig.db;
        usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);
        usersService = new UsersServiceImpl(usersRepository);
    }

    @ParameterizedTest
    @DisplayName("sing_up_with_values_returns_right_result_jdbc_template")
    @ValueSource(strings = {"qylenett@yandex.com", "sparrvio@yandex.com", "morfinpo@yandex.com", "jettajac@yandex.com", "delilahl@yandex.com"})
    void sing_up_with_values_returns_right_result_jdbc_template(String email) {
        String password = usersService.signUp(email);
        assertNotNull(password);
    }
}
