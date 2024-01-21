package soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import soa.entities.Title;

import java.util.List;

public interface TitleRepository extends JpaRepository<Title, Long> {

    // JPQL query to select all titles
    @Query("SELECT t FROM Title t")
    List<Title> findAllTitles();

    // JPQL query to select a title by its ID
    @Query("SELECT t FROM Title t WHERE t.id = :titleId")
    Title findTitleById(Long titleId);

    // Vous pouvez ajouter d'autres méthodes de requête personnalisées ici si nécessaire

    // Si vous voulez une méthode qui recherche des titres par leur nom, vous pouvez l'ajouter de cette manière
    List<Title> findTitleByNameContaining(String name);
}

