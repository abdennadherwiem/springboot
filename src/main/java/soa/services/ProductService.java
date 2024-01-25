package soa.services;

import soa.entities.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Long id);

    List<Product> getAllProductsByCategory(String Category);

    Product addProduct(Product product);

    Product updateProduct(Long id, Product updatedProduct);

    void deleteProduct(Long id);

    List<Product> searchProductsByName(String name);

    //recuperer rating de chaque title
    Map<String, Double> getAllRatings();



    ResponseEntity<String> getTitleWithMaxRating();

    ResponseEntity<String> getTitleWithMinRating();

















}
