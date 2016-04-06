package de.dhbw.ds.cdn.controller;

import de.dhbw.ds.cdn.data.Cart;
import de.dhbw.ds.cdn.data.Product;
import de.dhbw.ds.cdn.repositries.CartRepository;
import de.dhbw.ds.cdn.repositries.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by jbeisiegel on 06.04.16.
 */
public class CartController {

    CartRepository cartRepository;
    ProductRepository productRepository;

    @Autowired
    public CartController(CartRepository cartRepository, ProductRepository productRepository){
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @RequestMapping("/addToCart")
    public Iterable<Product> addToCart(@RequestParam(value = "id") UUID id, @RequestParam(value = "user") UUID userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if(cart.equals(null)){
            cart = new Cart();
            cart.setId(UUID.randomUUID());
            cart.setUserId(userId);
            cart.setProducts(new ArrayList<>());
        }
        cart.getProducts().add(id);
        cartRepository.save(cart);
        List<Product> prod = new ArrayList<>();
        for(UUID uuid : cart.getProducts()){
            prod.add(productRepository.findById(uuid));
        }
        return prod;
    }

    @RequestMapping("/removeFromCart")
    public Iterable<Product> removeFromCart(@RequestParam(value = "id") UUID id, @RequestParam(value = "user") UUID userId) {
        Cart cart = cartRepository.findByUserId(userId);
        for(int i=0;i<cart.getProducts().size();i++){
            if(cart.getProducts().get(i).equals(id)){
                cart.getProducts().remove(i);
            }
        }
        cartRepository.save(cart);
        List<Product> prod = new ArrayList<>();
        for(UUID uuid : cart.getProducts()){
            prod.add(productRepository.findById(uuid));
        }
        return prod;
    }
}
