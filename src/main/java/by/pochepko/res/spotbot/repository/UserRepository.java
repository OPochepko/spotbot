package by.pochepko.res.spotbot.repository;

import by.pochepko.res.spotbot.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByUsername(String username);

}
