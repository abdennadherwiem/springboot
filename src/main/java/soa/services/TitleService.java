package soa.services;



import java.util.List;

public interface TitleService {

    // Obtenir tous les titres
    List<String> getAllTitles();

    // Ajouter un titre
    String addTitle(String title);

    // Mettre à jour un titre
    String updateTitle(Long id, String updatedTitle);

    // Supprimer un titre
    void deleteTitle(Long id);
}

