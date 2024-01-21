package soa.services;

import soa.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Long id);

    List<Product> getAllProductsByCategory(String Category);

    Product addProduct(Product product);

    Product updateProduct(Long id, Product updatedProduct);

    void deleteProduct(Long id);

    List<Product> searchProductsByName(String name);
















}
