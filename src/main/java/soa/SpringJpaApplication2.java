package soa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import soa.entities.Product;
import soa.services.ProductService;

import java.util.List;

@SpringBootApplication
public class SpringJpaApplication2 implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(SpringJpaApplication2.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
