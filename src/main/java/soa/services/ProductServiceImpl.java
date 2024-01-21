package soa.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import soa.entities.Product;
import soa.repository.ProductRepository;
import java.util.stream.Collectors;


import java.util.List;

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









}

