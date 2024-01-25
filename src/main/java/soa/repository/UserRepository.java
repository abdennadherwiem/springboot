package soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import soa.entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    @Query("SELECT u FROM User u WHERE u.username = :username")
    List<User> findByUsersname(String username);


    long count();
}
