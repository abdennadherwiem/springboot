package soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import soa.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // JPQL query to select all products
    @Query("SELECT p FROM Product p")
    List<Product> findAllProducts();

    // JPQL query to select a product by its ID
    @Query("SELECT p FROM Product p WHERE p.id = :productId")
    Product findProductById(Long productId);
    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findProductByCategory(String category);

    List<Product> findProductByTitleContaining(String title);

}
