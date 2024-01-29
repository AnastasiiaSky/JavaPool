package school21.spring.service.application;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.services.UsersService;

import javax.sql.DataSource;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class)) {
            DataSource dataSource = context.getBean("hikariDataSource", HikariDataSource.class);
            DataBaseInitializing init = new DataBaseInitializing(dataSource);
            init.initDataBase();
            UsersService usersService = context.getBean("userServiceBean", UsersService.class);
            usersService.signUp("qylenett@gmail.com");
            usersService.signUp("sparrvio@gmail.com");
            UsersRepository usersRepository = context.getBean("jdbcTemplateBean", UsersRepository.class);
            System.out.println(usersRepository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
