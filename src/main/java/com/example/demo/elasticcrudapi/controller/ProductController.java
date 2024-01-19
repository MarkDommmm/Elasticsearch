package com.example.demo.elasticcrudapi.controller;

import com.example.demo.elasticcrudapi.domain.Product;
import com.example.demo.elasticcrudapi.service.ProductService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {


    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product savedProduct = productService.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable String id) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent()) {
            return new ResponseEntity<>(optionalProduct.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        Iterable<Product> products = productService.findAll();
        List<Product> productList = new ArrayList<>();
        products.forEach(productList::add);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        Optional<Product> optionalProduct = productService.findById(id);

        if (optionalProduct.isPresent()) {
            productService.delete(optionalProduct.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/productByName/{name}")
    public List<Product> getProductsByName( @PathVariable(value = "name") String name){
       return productService.findProductsByName(name);
    }

    @GetMapping("/findPrice/findByPriceBetween/{minPrice}/{maxPrice}")
    List<Product> findByPriceBetween( @PathVariable double minPrice, @PathVariable double maxPrice) {
        return productService.findByPriceBetween(minPrice, maxPrice);
    }



    @GetMapping("/fuzzySearch/{name}")
    public List<Product> fuzzySearchByName( @PathVariable(value = "name") String name){
        return productService.fuzzySearch(name);
    }

    @GetMapping("/multiMatchQuery/{name}")
    public List<Product> multiMatchQuery( @PathVariable(value = "name") String name){
        return productService.multiMatchQuery(name);
    }




}

