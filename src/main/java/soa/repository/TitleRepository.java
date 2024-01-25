package soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soa.entities.Title;

public interface TitleRepository extends JpaRepository<Title, Long> {

    Title findByName(String name);

    // You can add custom queries if needed

    long count();
}
