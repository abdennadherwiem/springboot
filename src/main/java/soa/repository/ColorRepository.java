package soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import soa.entities.Color;

import java.util.List;

public interface ColorRepository extends JpaRepository<Color, Long> {

    // JPQL query to select all colors
    @Query("SELECT c FROM Color c")
    List<Color> findAllColors();

    // JPQL query to select a color by its ID
    @Query("SELECT c FROM Color c WHERE c.id = :colorId")
    Color findColorById(Long colorId);

    // Vous pouvez ajouter d'autres méthodes de requête personnalisées ici si nécessaire

    // Si vous voulez une méthode qui recherche des couleurs par leur nom, vous pouvez l'ajouter de cette manière
    List<Color> findColorByNameContaining(String name);
}
