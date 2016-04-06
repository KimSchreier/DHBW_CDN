package de.dhbw.ds.cdn.controller;

import de.dhbw.ds.cdn.data.Product;
import de.dhbw.ds.cdn.data.Purches;
import de.dhbw.ds.cdn.repositries.ProductRepository;
import de.dhbw.ds.cdn.repositries.PurchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by jbeisiegel on 06.04.16.
 */
public class PurchController {

    PurchRepository purchRepository;
    ProductRepository productRepository;

    @Autowired
    public PurchController(PurchRepository purchRepository, ProductRepository productRepository){
        this.purchRepository = purchRepository;
        this.productRepository = productRepository;
    }

    @RequestMapping("/purches")
    public List<Product> getProducts(@RequestParam(value = "user") UUID userId) {
        Purches purche = purchRepository.findByUserId(userId);
        List<UUID> productIds = purche.getProducts();
        ArrayList<Product> products = new ArrayList<>();
        for(UUID id : productIds){
            products.add(productRepository.findById(id));
        }
        return products;
    }
}
