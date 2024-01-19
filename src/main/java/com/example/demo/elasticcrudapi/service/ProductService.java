package com.example.demo.elasticcrudapi.service;

import com.example.demo.elasticcrudapi.domain.Product;
import com.example.demo.elasticcrudapi.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {


    private final ProductRepository productRepository;

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;


    public Product save(Product product) {

        try {
            return productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }


    public void delete(Product product) {
        productRepository.delete(product);
    }


    public List<Product> findByPriceBetween(double minPrice, double maxPrice) {
        QueryBuilder rangeQueryBuilder = QueryBuilders
                .rangeQuery("ListPrice").gte(minPrice).lte(maxPrice);
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(rangeQueryBuilder).build();
        SearchHits<Product> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, Product.class);
        return searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }


    public List<Product> findProductsByName(final String searchKeyword) {
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("Name", searchKeyword);
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQueryBuilder).build();
        SearchHits<Product> searchHits = elasticsearchRestTemplate
                .search(nativeSearchQuery, Product.class);
        return searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }


    public List<Product> fuzzySearch(String nameKeyword) {
        NativeSearchQuery fuzzinessQueryBuilder1 = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("Name", nameKeyword)
                        .fuzziness(Fuzziness.ONE)
                        .prefixLength(3))
                .build();
        SearchHits<Product> searchHits = elasticsearchRestTemplate
                .search(fuzzinessQueryBuilder1, Product.class);
        return searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

    public List<Product> multiMatchQuery(String nameKeyword) {
        QueryBuilder multiMatchQueryBuilder = QueryBuilders
                .multiMatchQuery(nameKeyword, "Color", "Name");
        NativeSearchQuery multiMatchSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQueryBuilder).build();
        SearchHits<Product> searchHits = elasticsearchRestTemplate
                .search(multiMatchSearchQuery, Product.class);
        return searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }


}

