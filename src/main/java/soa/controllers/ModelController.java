package soa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import soa.entities.Model;
import soa.services.ModelService;

import java.util.List;

@RestController
@RequestMapping("/models")
@CrossOrigin(origins = "http://localhost:3000")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @GetMapping("/all")
    public List<String> getAllModels() {
        return modelService.getAllModels();
    }

    @PostMapping("/add")
    public String addModel(@RequestBody String modelName) {
        return modelService.addModel(modelName);
    }

    @PutMapping("/update/{id}")
    public String updateModel(@PathVariable Long id, @RequestBody String updatedModelName) {
        return modelService.updateModel(id, updatedModelName);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteModel(@PathVariable Long id) {
        modelService.deleteModel(id);
    }
}
