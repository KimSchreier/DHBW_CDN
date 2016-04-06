package de.dhbw.ds.cdn.repositries;

import de.dhbw.ds.cdn.data.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.UUID;


public interface UserRepository extends CassandraRepository<User> {

    @Query("SELECT*FROM users WHERE login=?0 ALLOW FILTERING")
    User findByLogin(String login);

    @Query("SELECT*FROM users WHERE id=?0")
    User findById(UUID id);
}
