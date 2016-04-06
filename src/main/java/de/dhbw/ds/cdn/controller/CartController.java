package de.dhbw.ds.cdn.controller;

import de.dhbw.ds.cdn.data.Cart;
import de.dhbw.ds.cdn.data.Product;
import de.dhbw.ds.cdn.repositries.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by jbeisiegel on 06.04.16.
 */
public class CartController {

    CartRepository cartRepository;

    @Autowired
    public CartController(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    @RequestMapping("/addToCart")
    public boolean addToCart(@RequestParam(value = "id") UUID id, @RequestParam(value = "user") UUID userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if(cart.equals(null)){
            cart = new Cart();
            cart.setId(UUID.randomUUID());
            cart.setUserId(userId);
            cart.setProducts(new ArrayList<>());
        }
        cart.getProducts().add(id);
        cartRepository.save(cart);
        return true;
    }
}
