package de.dhbw.ds.cdn.repositries;

import de.dhbw.ds.cdn.data.Product;
import de.dhbw.ds.cdn.data.Purches;
import de.dhbw.ds.cdn.data.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.UUID;

/**
 * Created by jbeisiegel on 06.04.16.
 */
public interface ProductRepository extends CassandraRepository<Product> {

    @Query("SELECT*FROM product WHERE id=?0")
    Product findById(UUID id);
}
