package de.dhbw.ds.cdn.controller;

import de.dhbw.ds.cdn.data.Product;
import de.dhbw.ds.cdn.data.Purches;
import de.dhbw.ds.cdn.repositries.CartRepository;
import de.dhbw.ds.cdn.repositries.ProductRepository;
import de.dhbw.ds.cdn.repositries.PurchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by jbeisiegel on 06.04.16.
 */
@RestController
public class PurchController {

    PurchRepository purchRepository;
    ProductRepository productRepository;
    CartRepository cartRepository;

    @Autowired
    public PurchController(PurchRepository purchRepository, ProductRepository productRepository, CartRepository cartRepository){
        this.purchRepository = purchRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    @RequestMapping("/purches")
    public Iterable<PurchaisDTO> getPurches(@RequestParam(value = "user") String sid) {
        UUID userId = UUID.fromString(sid);
        Iterable<Purches> purche = purchRepository.findByUserId(userId);
        List<PurchaisDTO> dto = new ArrayList<>();
        for(Purches pur : purche){
            PurchaisDTO p = new PurchaisDTO();
            p.setCreationDate(pur.getCreationDate());
            p.setUserId(pur.getUserId());
            p.setId(pur.getId());
            ArrayList<Product> prod = new ArrayList<>();
            for(UUID productId: pur.getProducts()){
                prod.add(productRepository.findById(productId));
            }
            p.setProducts(prod);
            dto.add(p);
        }
        return dto;
    }

    @RequestMapping("/placePurches")
    public Iterable<PurchaisDTO> placePurches(@RequestParam(value = "user") String sid){
        UUID userId = UUID.fromString(sid);
        Purches purch = new Purches();
        purch.setId(UUID.randomUUID());
        purch.setProducts(cartRepository.findByUserId(userId).getProducts());
        cartRepository.delete(cartRepository.findByUserId(userId));
        purch.setUserId(userId);
        purch.setCreationDate(new Date());
        purchRepository.save(purch);
        Iterable<Purches> purche = purchRepository.findByUserId(userId);
        List<PurchaisDTO> dto = new ArrayList<>();
        for(Purches pur : purche){
            PurchaisDTO p = new PurchaisDTO();
            p.setCreationDate(pur.getCreationDate());
            p.setUserId(pur.getUserId());
            p.setId(pur.getId());
            ArrayList<Product> prod = new ArrayList<>();
            for(UUID productId: pur.getProducts()){
                prod.add(productRepository.findById(productId));
            }
            p.setProducts(prod);
            dto.add(p);
        }
        return dto;
    }
}
