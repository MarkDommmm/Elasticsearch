package hh.service;

import hh.domain.Product;
 import hh.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        return productRepository.save(product);
    }

    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }

    public Iterable<Product> findAll() {
        Pageable pageable = PageRequest.of(0, 1000);
        return productRepository.findAll(pageable).getContent();
    }


    public void delete(Product product) {
        productRepository.delete(product);
    }


    public List<Product> findByPriceBetween(double minPrice, double maxPrice) {
        QueryBuilder rangeQueryBuilder = QueryBuilders
                .rangeQuery("price").gte(minPrice).lte(maxPrice);
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(rangeQueryBuilder).build();
        SearchHits<Product> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, Product.class);
        return searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }


    public List<Product> findProductsByName(final String query) {
        // 1. Create a match query for the "Name" field with the provided query
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", query);

        // 2. Build a NativeSearchQuery using the match query
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQueryBuilder).build();

        // 3. Perform the search using ElasticsearchRestTemplate and get the search hits
        SearchHits<Product> searchHits = elasticsearchRestTemplate
                .search(nativeSearchQuery, Product.class);

        // 4. Map the search hits to a list of Product objects and return the result
        return searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }


    public Product findProductById(int id) {
        QueryBuilder queryBuilder = QueryBuilders.termQuery("ProductID", id);

        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder).build();

        SearchHits<Product> searchHits = elasticsearchRestTemplate
                .search(nativeSearchQuery, Product.class);

        if (searchHits.getTotalHits() > 0) {
            return searchHits.getSearchHit(0).getContent();
        }

        return null;

    }


    public List<Product> fuzzySearch(String query) {

        // 2. Create a NativeSearchQuery using the regular expression query
        NativeSearchQuery wildcardQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.wildcardQuery("name", "*" + query + "*"))
                .build();

// 3. Perform the search using the ElasticsearchRestTemplate and get the search hits
        SearchHits<Product> searchHits = elasticsearchRestTemplate
                .search(wildcardQuery, Product.class);

// 4. Map the search hits to a list of Product objects and return the result
        return searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());

    }


    public List<Product> termQuery(String query) {
        QueryBuilder termQuery = QueryBuilders.termQuery("name", query);
        NativeSearchQuery termQuerySearchQuery = new NativeSearchQueryBuilder()
                .withQuery(termQuery).build();
        SearchHits<Product> searchHits = elasticsearchRestTemplate
                .search(termQuerySearchQuery, Product.class);
        return searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

    public List<Product> multiMatchQuery(String query) {
        QueryBuilder multiMatch = QueryBuilders
                .multiMatchQuery(query, "color", "name");
        NativeSearchQuery multiMatchSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(multiMatch).build();
        SearchHits<Product> searchHits = elasticsearchRestTemplate
                .search(multiMatchSearchQuery, Product.class);
        return searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }


}

