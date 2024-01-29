package school21.spring.service.application;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

import java.util.List;
import java.util.Optional;


public class Main {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("context.xml")) {

            DriverManagerDataSource ds = context.getBean("driverManagerDataSource", DriverManagerDataSource.class);
            DataBaseInitializing init = new DataBaseInitializing(ds);
            init.initDataBase();

            UsersRepositoryJdbcImpl usersRepositoryJdbc =
                    context.getBean("UsersRepositoryJdbcImpl", UsersRepositoryJdbcImpl.class);

            UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate =
                    context.getBean("usersRepositoryJdbcTemplate", UsersRepositoryJdbcTemplateImpl.class);

            createUsers(usersRepositoryJdbc, usersRepositoryJdbcTemplate);
            findByIdTest(usersRepositoryJdbc, usersRepositoryJdbcTemplate);
            findAllTest(usersRepositoryJdbc, usersRepositoryJdbcTemplate);
            updateTest(usersRepositoryJdbc, usersRepositoryJdbcTemplate);
            deleteTest(usersRepositoryJdbc, usersRepositoryJdbcTemplate);
            findByEmailTest(usersRepositoryJdbc, usersRepositoryJdbcTemplate);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void createUsers(UsersRepositoryJdbcImpl usersRepositoryJdbc,
                                   UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate) {
        usersRepositoryJdbc.save(new User("qylenett@mail.ru"));
        usersRepositoryJdbc.save(new User("morfinpo@mail.ru"));
        usersRepositoryJdbc.save(new User("jettajac@mail.ru"));
        usersRepositoryJdbc.save(new User("delilahl@mail.ru"));
        usersRepositoryJdbc.save(new User("sparrvio@mail.ru"));


        usersRepositoryJdbcTemplate.save(new User("qylenett@yandex.ru"));
        usersRepositoryJdbcTemplate.save(new User("morfinpo@yandex.ru"));
        usersRepositoryJdbcTemplate.save(new User("jettajac@yandex.ru"));
        usersRepositoryJdbcTemplate.save(new User("delilahl@yandex.ru"));
        usersRepositoryJdbcTemplate.save(new User("sparrvio@yandex.ru"));

    }

    public static void findByIdTest(UsersRepositoryJdbcImpl usersRepositoryJdbc,
                                    UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate) {

        Optional<User> result = usersRepositoryJdbc.findById(2L);
        if (result.isPresent()) {
            User resultUser = result.get();
        }
        Optional<User> result1 = usersRepositoryJdbcTemplate.findById(3L);

        if (result1.isPresent()) {
            User resultUser = result.get();
        }
    }

    public static void findAllTest(UsersRepositoryJdbcImpl usersRepositoryJdbc,
                                   UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate) {
        List<User> users = usersRepositoryJdbc.findAll();
        List<User> users1 = usersRepositoryJdbcTemplate.findAll();
    }

    public static void updateTest(UsersRepositoryJdbcImpl usersRepositoryJdbc,
                                  UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate) {
        usersRepositoryJdbc.update(new User(1L, "qylenett@mail.com"));
        usersRepositoryJdbcTemplate.update(new User(2L, "morfinpo@mail.com"));
    }

    public static void deleteTest(UsersRepositoryJdbcImpl usersRepositoryJdbc,
                                  UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate) {
        usersRepositoryJdbc.delete(3L);
        usersRepositoryJdbcTemplate.delete(4L);
    }

    public static void findByEmailTest(UsersRepositoryJdbcImpl usersRepositoryJdbc,
                                       UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate) {
        Optional<User> user = usersRepositoryJdbc.findByEmail("qylenett@mail.com");
        if (user.isPresent()) {
            User user1 = user.get();
        }
        Optional<User> user1 = usersRepositoryJdbcTemplate.findByEmail("sparrvio@mail.ru");

        if (user1.isPresent()) {
            User user2 = user1.get();
        }
    }

}
