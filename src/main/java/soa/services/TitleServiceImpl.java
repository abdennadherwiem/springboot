package soa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soa.entities.Title;
import soa.repository.TitleRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TitleServiceImpl implements TitleService {

    @Autowired
    private TitleRepository titleRepository;

    @Override
    public Title createTitle(Title title) {
        return titleRepository.save(title);
    }

    @Override
    public Title getTitleById(Long id) {
        return titleRepository.findById(id).orElse(null);
    }

    @Override
    public Title getTitleByName(String name) {
        return titleRepository.findByName(name);
    }

    @Override
    public Map<String, Object> someTitleOperation() {
        // Add your custom title-related logic here
        Map<String, Object> result = new HashMap<>();
        result.put("message", "Some title operation completed");
        return result;
    }

    @Override
    public List<Title> getAllTitles() {
        return titleRepository.findAll();
    }

    @Override
    public Title updateTitle(Long id, Title titleUpdate) {
        Title existingTitle = titleRepository.findById(id).orElse(null);

        if (existingTitle != null) {
            existingTitle.setName(titleUpdate.getName());
            return titleRepository.save(existingTitle);
        }

        return null;
    }

    @Override
    public void deleteTitle(Long id) {
        titleRepository.deleteById(id);
    }

    @Override
    public long countTitles() {
        return titleRepository.count();
    }

}

