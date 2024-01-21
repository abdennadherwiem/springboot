package soa.services;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import soa.entities.Product;
import soa.entities.User;
import soa.repository.UserRepository;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping( "/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final long EXPIRATION_TIME = 864_000_000;

    @Override
    @PostMapping(value = "/register")
    @ResponseBody
    public User registerUser(@RequestBody User user) {
        // Check if the username is already taken
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }

        String hashedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }

    @Override
    @ResponseBody
    @PostMapping(value = "/login")
    public Map<String,Object> loginUser(@RequestBody User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser == null || !new BCryptPasswordEncoder().matches(user.getPassword(), existingUser.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }
        boolean userType = existingUser.getUserType();

        // Generate JWT token
        String token = Jwts.builder()
                .setSubject(existingUser.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();


        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("userType", userType);

        // Return the map as the response
        return response;    }
    @GetMapping(value= "/")
    @ResponseBody
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @PutMapping(value = "/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userupdate) {
        User existinguser = userRepository.findById(id).orElse(null);

        if (existinguser != null) {
            existinguser.setUsername(userupdate.getUsername());
            existinguser.setPassword(userupdate.getPassword());
            return userRepository.save(existinguser);
        }

        return null;
    }
    @DeleteMapping(value = "/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
    @GetMapping(value= "/{id}")
    @ResponseBody
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @GetMapping(value = "/search/{name}")
    @ResponseBody
    public List<User> searchProductsByName(@PathVariable String name) {
        return userRepository.findByUsersname(name);
    }
}
