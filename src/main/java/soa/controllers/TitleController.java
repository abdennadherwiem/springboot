package soa.controllers;

// TitleController.java


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import soa.entities.Title;
import soa.services.TitleService;

import java.util.List;

@RestController
@RequestMapping("/titles")
@CrossOrigin(origins = "http://localhost:3000")
public class TitleController {

    @Autowired
    private TitleService titleService;

    @GetMapping("/all")
    public List<String> getAllTitles() {
        return titleService.getAllTitles();
    }

    @PostMapping("/add")
    public String addTitle(@RequestBody String titleName) {
        return titleService.addTitle(titleName);
    }

    @PutMapping("/update/{id}")
    public String updateTitle(@PathVariable Long id, @RequestBody String updatedTitleName) {
        return titleService.updateTitle(id, updatedTitleName);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTitle(@PathVariable Long id) {
        titleService.deleteTitle(id);
    }
}
