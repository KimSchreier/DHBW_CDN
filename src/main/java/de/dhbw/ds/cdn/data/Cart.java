package de.dhbw.ds.cdn.data;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.ArrayList;
import java.util.UUID;

@Table(value = "cart")
public class Cart {

    @PrimaryKeyColumn(name = "id",ordinal = 1,type = PrimaryKeyType.CLUSTERED)
    private UUID id;

    public ArrayList<UUID> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<UUID> products) {
        this.products = products;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Column(value = "products")
    private ArrayList<UUID> products;

    @PrimaryKeyColumn(name="user",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
    private UUID userId;
}
