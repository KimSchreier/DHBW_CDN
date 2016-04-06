package de.dhbw.ds.cdn.controller;

import de.dhbw.ds.cdn.data.Product;
import de.dhbw.ds.cdn.data.Purches;
import de.dhbw.ds.cdn.repositries.ProductRepository;
import de.dhbw.ds.cdn.repositries.PurchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by jbeisiegel on 06.04.16.
 */
@RestController
public class PurchController {

    PurchRepository purchRepository;
    ProductRepository productRepository;

    @Autowired
    public PurchController(PurchRepository purchRepository, ProductRepository productRepository){
        this.purchRepository = purchRepository;
        this.productRepository = productRepository;
    }

    @RequestMapping("/purches")
    public Iterable<Purches> getProducts(@RequestParam(value = "user") String sid) {
        UUID userId = UUID.fromString(sid);
        Iterable<Purches> purche = purchRepository.findByUserId(userId);
        return purche;
    }
}
