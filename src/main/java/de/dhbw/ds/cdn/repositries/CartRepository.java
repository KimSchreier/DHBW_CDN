package de.dhbw.ds.cdn.repositries;

import de.dhbw.ds.cdn.data.Cart;
import de.dhbw.ds.cdn.data.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.UUID;

public interface CartRepository extends CassandraRepository<Cart> {

    @Query("SELECT*FROM cart WHERE user=?0")
    User findByUserId(UUID id);
}
