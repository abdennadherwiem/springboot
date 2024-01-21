package soa.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soa.entities.Title;
import soa.repository.TitleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TitleServiceImpl implements TitleService {

    @Autowired
    private TitleRepository titleRepository;

    @Override
    public List<String> getAllTitles() {
        List<Title> allTitles = titleRepository.findAll();
        return allTitles.stream()
                .map(Title::getName)
                .collect(Collectors.toList());
    }

    @Override
    public String addTitle(String titleName) {
        // Ajouter un titre s'il n'existe pas déjà
        if (titleRepository.findTitleByNameContaining(titleName).isEmpty()) {
            Title newTitle = new Title();
            newTitle.setName(titleName);
            titleRepository.save(newTitle);
            return "Titre ajouté avec succès.";
        } else {
            return "Le titre existe déjà.";
        }
    }

    @Override
    public String updateTitle(Long id, String updatedTitleName) {
        Title existingTitle = titleRepository.findById(id).orElse(null);

        if (existingTitle != null) {
            existingTitle.setName(updatedTitleName);
            titleRepository.save(existingTitle);
            return "Titre mis à jour avec succès.";
        } else {
            return "Titre non trouvé.";
        }
    }

    @Override
    public void deleteTitle(Long id) {
        titleRepository.deleteById(id);
    }
}


