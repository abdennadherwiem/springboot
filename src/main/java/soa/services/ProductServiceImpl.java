package soa.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import soa.entities.Product;
import soa.repository.ProductRepository;
import java.util.stream.Collectors;

import java.util.Map;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;

import java.util.Comparator;
import org.springframework.http.ResponseEntity;


@RestController // pour déclarer un service web de type REST
@RequestMapping("/products") // http://localhost:8080/product
@CrossOrigin(origins = "http://localhost:3000")
public class ProductServiceImpl implements ProductService  {

    @Autowired
    private ProductRepository productRepository;
    @GetMapping(value= "/")
    @ResponseBody
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    @GetMapping(value= "/{id}")
    @ResponseBody
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    @GetMapping(value= "/category/{Category}")
    @ResponseBody
    public List<Product> getAllProductsByCategory(@PathVariable String Category) {
        return productRepository.findProductByCategory(Category);
    }
    @PostMapping(value = "/add")
    @ResponseBody
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping(value = "/update/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product existingProduct = productRepository.findById(id).orElse(null);

        if (existingProduct != null) {
            existingProduct.setTitle(updatedProduct.getTitle());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setCategory(updatedProduct.getCategory());
            existingProduct.setImage(updatedProduct.getImage());
            existingProduct.setRating(updatedProduct.getRating());

            return productRepository.save(existingProduct);
        }

        return null;
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

    @GetMapping(value = "/search/{name}")
    @ResponseBody
    public List<Product> searchProductsByName(@PathVariable String name) {
        return productRepository.findProductByTitleContaining(name);
    }


    @GetMapping(value = "/count")
    @ResponseBody
    public long countProducts() {
        return productRepository.count();
    }

    @GetMapping(value = "/allTitles")
    @ResponseBody
    public List<String> getAllProductTitles() {
        return productRepository.findAll()
                .stream()
                .map(Product::getTitle)
                .collect(Collectors.toList());
    }

    @GetMapping("/allRatings")
    @ResponseBody
    @Transactional
    public Map<String, Double> getAllRatings() {
        List<Product> allProducts = productRepository.findAll();
        Map<String, Double> ratingsMap = new HashMap<>();

        for (Product product : allProducts) {
            ratingsMap.put(product.getTitle(), product.getRating());
        }

        return ratingsMap;
    }


    //wiem
    @GetMapping("/titleWithMaxRating")
    @ResponseBody
    public ResponseEntity<String> getTitleWithMaxRating() {
        return ResponseEntity.ok(getTitleWithRating(true));
    }

    @GetMapping("/titleWithMinRating")
    @ResponseBody
    public ResponseEntity<String> getTitleWithMinRating() {
        return ResponseEntity.ok(getTitleWithRating(false));
    }

    // ... autres méthodes du service

    private String getTitleWithRating(boolean isMaxRating) {
        List<Product> allProducts = productRepository.findAll();

        if (allProducts.isEmpty()) {
            return "Aucun produit disponible.";
        }

        Comparator<Product> ratingComparator = Comparator.comparing(Product::getRating);

        Product productWithRating = isMaxRating ?
                allProducts.stream().max(ratingComparator).orElse(null) :
                allProducts.stream().min(ratingComparator).orElse(null);

        if (productWithRating == null) {
            return "Produit avec la note non trouvé.";
        }

        return "" + productWithRating.getTitle();
    }











}

