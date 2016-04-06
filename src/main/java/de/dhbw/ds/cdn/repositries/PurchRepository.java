package de.dhbw.ds.cdn.repositries;

import de.dhbw.ds.cdn.data.Purches;
import de.dhbw.ds.cdn.data.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.UUID;

/**
 * Created by jbeisiegel on 06.04.16.
 */
public interface PurchRepository extends CassandraRepository<Purches> {

    @Query("SELECT*FROM purches WHERE user=?0")
    Iterable<Purches> findByUserId(UUID id);
}