package soa.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soa.entities.Model; // Assuming you have a Model entity
import soa.repository.ModelRepository; // Assuming you have a ModelRepository

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public List<String> getAllModels() {
        List<Model> allModels = modelRepository.findAll();
        return allModels.stream()
                .map(Model::getModelName)
                .collect(Collectors.toList());
    }

    @Override
    public String addModel(String modelName) {
        // Add a model if it does not already exist
        if (modelRepository.findModelByModelNameContaining(modelName).isEmpty()) {
            Model newModel = new Model();
            newModel.setModelName(modelName);
            modelRepository.save(newModel);
            return "Model added successfully.";
        } else {
            return "The model already exists.";
        }
    }

    @Override
    public String updateModel(Long id, String updatedModelName) {
        Model existingModel = modelRepository.findById(id).orElse(null);

        if (existingModel != null) {
            existingModel.setModelName(updatedModelName);
            modelRepository.save(existingModel);
            return "Model updated successfully.";
        } else {
            return "Model not found.";
        }
    }

    @Override
    public void deleteModel(Long id) {
        modelRepository.deleteById(id);
    }


    //wiem search by model
    @Override
    public List<String> searchModelsByName(String name) {
        List<Model> matchingModels = modelRepository.findModelByModelNameContaining(name);
        return matchingModels.stream()
                .map(Model::getModelName)
                .collect(Collectors.toList());
    }



}

