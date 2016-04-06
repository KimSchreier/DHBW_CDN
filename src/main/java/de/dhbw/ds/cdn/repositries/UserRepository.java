package de.dhbw.ds.cdn.repositries;

import de.dhbw.ds.cdn.data.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;


public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);

    User findById(UUID id);
}
