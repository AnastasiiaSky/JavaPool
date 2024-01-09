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

import static org.junit.jupiter.api.Assertions.*;

public class UsersServiceImplTest {
    private UsersRepository usersRepository = Mockito.mock(UsersRepository.class);
    private UsersServiceImpl usersService = new UsersServiceImpl(usersRepository);

    @Test
    void authenticationSuccess() {
        User qylenett = new User(1L, "qylenett", "12345", false);
        Mockito.when(usersRepository.findByLogin(Mockito.eq("qylenett"))).thenReturn(qylenett);
        try {
            assertTrue(usersService.authenticate("qylenett", "12345"));
        } catch (AlreadyAuthenticatedException | EntityNotFoundException exception) {
            exception.printStackTrace();
        }
    }


    @Test
    void authenticationIncorrectLogin() {
        User qylenett = new User(1L, "qylenett", "12345", false);
        Mockito.when(usersRepository.findByLogin(Mockito.eq("qylenett"))).thenReturn(qylenett);
        Throwable exception = assertThrows(
                EntityNotFoundException.class,
                () -> usersService.authenticate("qylen", "12345")
        );
        assertEquals("EntityNotFoundException: User not found", exception.toString());
    }

    @Test
    void authenticationIncorrectPassword() {
        User qylenett = new User(1L, "qylenett", "12345", false);

        Mockito.when(usersRepository.findByLogin(Mockito.eq("qylenett"))).thenReturn(qylenett);
        Throwable exception = assertThrows(
                EntityNotFoundException.class,
                () -> usersService.authenticate("qylenett", "11111")
        );
        assertEquals("EntityNotFoundException: Incorrect password", exception.toString());
    }

    @Test
    void authenticationAlreadyAuthenticate() {
        User qylenett = new User(1L, "qylenett", "12345", true);

        Mockito.when(usersRepository.findByLogin(Mockito.eq("qylenett"))).thenReturn(qylenett);
        Throwable exception = assertThrows(
                AlreadyAuthenticatedException.class,
                () -> usersService.authenticate("qylenett", "12345")
        );
        assertEquals("AlreadyAuthenticatedException: User already authenticated", exception.toString());
    }
}
