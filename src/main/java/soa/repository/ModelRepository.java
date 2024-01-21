package soa.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import soa.entities.Model;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {

    // JPQL query to select all models
    @Query("SELECT m FROM Model m")
    List<Model> findAllModels();

    // JPQL query to select a model by its ID
    @Query("SELECT m FROM Model m WHERE m.id = :modelId")
    Model findModelById(Long modelId);

    // You can add other custom query methods here if needed

    // If you want a method that searches for models by their name, you can add it like this
    List<Model> findModelByModelNameContaining(String modelName);
}
