package de.dhbw.ds.cdn.controller;

import de.dhbw.ds.cdn.data.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by jbeisiegel on 06.04.16.
 */
public class PurchaisDTO {

    private UUID id;

    private ArrayList<Product> products;

    private UUID userId;

    private Date creationDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
