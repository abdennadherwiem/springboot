package soa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import soa.entities.Title;
import soa.services.TitleService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/titles")
@CrossOrigin(origins = "http://localhost:3000")
public class TitleController {
    @Autowired
    private TitleService titleService;

    @PostMapping("/add")
    public Title createTitle(@RequestBody Title title) {
        return titleService.createTitle(title);
    }

    @GetMapping("/{id}")
    public Title getTitleById(@PathVariable Long id) {
        return titleService.getTitleById(id);
    }

    @GetMapping("/name/{name}")
    public Title getTitleByName(@PathVariable String name) {
        return titleService.getTitleByName(name);
    }

    @GetMapping("/all")
    public List<Title> getAllTitles() {
        return titleService.getAllTitles();
    }

    @PutMapping("/{id}")
    public Title updateTitle(@PathVariable Long id, @RequestBody Title titleUpdate) {
        return titleService.updateTitle(id, titleUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteTitle(@PathVariable Long id) {
        titleService.deleteTitle(id);
    }

    @GetMapping("/count")
    public long countTitles() {
        return titleService.countTitles();
    }

    @GetMapping("/custom-operation")
    public Map<String, Object> someTitleOperation() {
        return titleService.someTitleOperation();
    }
}
