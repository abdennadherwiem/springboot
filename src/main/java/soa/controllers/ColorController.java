package soa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import soa.entities.Color;
import soa.services.ColorService;

import java.util.List;

@RestController
@RequestMapping("/colors")
@CrossOrigin(origins = "http://localhost:3000")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @GetMapping("/all")
    public List<String> getAllColors() {
        return colorService.getAllColors();
    }

    @PostMapping("/add")
    public String addColor(@RequestBody String colorName) {
        return colorService.addColor(colorName);
    }

    @PutMapping("/update/{id}")
    public String updateColor(@PathVariable Long id, @RequestBody String updatedColorName) {
        return colorService.updateColor(id, updatedColorName);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteColor(@PathVariable Long id) {
        colorService.deleteColor(id);
    }
}
