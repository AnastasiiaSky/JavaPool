package school21.spring.service.repositories;

import java.util.Optional;

public interface UsersRepository<User> extends CrudRepository {
    Optional<User> findByEmail(String email);
}
