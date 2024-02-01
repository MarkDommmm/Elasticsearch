package hh.controller;

import hh.domain.Product;
import hh.service.ProductService;
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

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        Iterable<Product> products = productService.findAll();
        List<Product> productList = new ArrayList<>();
        products.forEach(productList::add);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product savedProduct = productService.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable String id) {
        Optional<Product> optionalProduct = productService.findById(id);
        return optionalProduct.map(product -> new ResponseEntity<>(product, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/findProductById/{id}")
    public Product findProductById(@PathVariable int id) {
        return productService.findProductById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        Optional<Product> optionalProduct = productService.findById(id);

        if (optionalProduct.isPresent()) {
            productService.delete(optionalProduct.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/productByName/{query}")
    public List<Product> getProductsByName(@PathVariable String query) {
        return productService.findProductsByName(query);
    }

    @GetMapping("/findByPriceBetween/{minPrice}/{maxPrice}")
    List<Product> findByPriceBetween(@PathVariable double minPrice, @PathVariable double maxPrice) {
        return productService.findByPriceBetween(minPrice, maxPrice);
    }


    @GetMapping("/fuzzySearch/{query}")
    public List<Product> fuzzySearchByName(@PathVariable String query) {
        return productService.fuzzySearch(query);
    }

    @GetMapping("/multiMatchQuery/{query}")
    public List<Product> multiMatchQuery(@PathVariable String query) {
        return productService.multiMatchQuery(query);
    }
    @GetMapping("/termQuery/{query}")
    public List<Product> termQuery(@PathVariable String query) {
        return productService.termQuery(query);
    }

}

