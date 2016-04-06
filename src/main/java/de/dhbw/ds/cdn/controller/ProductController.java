package de.dhbw.ds.cdn.controller;

import de.dhbw.ds.cdn.data.Product;
import de.dhbw.ds.cdn.repositries.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jbeisiegel on 06.04.16.
 */
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping("/products")
    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }
}
