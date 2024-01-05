package edu.school21.services;


import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsersServiceImplTest {
    private UsersRepository usersRepository = Mockito.mock(UsersRepository.class);
    private UsersServiceImpl usersService = new UsersServiceImpl(usersRepository);

    @Test
    void findByLoginTrue() {
        User qylenett = new User(1L, "qylenett", "12345", false);
        User sparrvio = new User(2L, "sparrvio", "12345", false);

        Mockito.when(usersRepository.findByLogin(Mockito.eq("qylenett"))).thenReturn(qylenett);
        try {
            assertTrue(usersService.authenticate("qylenett", "12345"));
        } catch (AlreadyAuthenticatedException | EntityNotFoundException exception) {
            exception.printStackTrace();
        }
        Mockito.when(usersRepository.findByLogin(Mockito.eq("sparrvio"))).thenReturn(sparrvio);

        try {
            assertTrue(usersService.authenticate("sparrvio", "12345"));
        } catch (AlreadyAuthenticatedException | EntityNotFoundException exception) {
            exception.printStackTrace();
        }
    }


//    @Test
//    void findByLoginIncorrectLogin() {
//        User qylenett = new User(1L, "qylenett", "12345", false);
//        User sparrvio = new User(2L, "sparrvio", "12345", false);
//
//        Mockito.when(usersRepository.findByLogin(Mockito.eq("qylenett"))).thenReturn(qylenett);
//        try {
//            assertFalse(usersService.authenticate("qylen", "12345"));
//        } catch (AlreadyAuthenticatedException | EntityNotFoundException exception) {
//            exception.printStackTrace();
//        }
//        Mockito.when(usersRepository.findByLogin(Mockito.eq("sparrvio"))).thenReturn(sparrvio);
//
//        try {
//            assertTrue(usersService.authenticate("sparrvio", "12345"));
//        } catch (AlreadyAuthenticatedException | EntityNotFoundException exception) {
//            exception.printStackTrace();
//        }
//    }
}
