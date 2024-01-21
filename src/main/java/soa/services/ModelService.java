package soa.services;



import java.util.List;

public interface ModelService {

    // Get all models
    List<String> getAllModels();

    // Add a model
    String addModel(String modelName);

    // Update a model
    String updateModel(Long id, String updatedModelName);

    // Delete a model
    void deleteModel(Long id);

    //wiem
    List<String> searchModelsByName(String name);
}

