package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import java.util.UUID;

@Component("userServiceBean")
public class UsersServiceImpl implements UsersService {
    private UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(@Qualifier("jdbcTemplateBean") UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public String signUp(String email) {
        if (checkEmail(email)) {
            String tmpPassword = String.valueOf(UUID.randomUUID());
            User currentUser = new User(email, tmpPassword);
            usersRepository.save(currentUser);
            return tmpPassword;
        }
        return null;
    }

    private boolean checkEmail(String email) {
        return (email != null && !email.isEmpty() &&
                email.contains("@") && email.contains("."));
    }
}
