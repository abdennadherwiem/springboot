package soa.services;

import java.util.List;

public interface ColorService {

    // Obtenir toutes les couleurs
    List<String> getAllColors();

    // Ajouter une couleur
    String addColor(String color);

    // Mettre à jour une couleur
    String updateColor(Long id, String updatedColor);

    // Supprimer une couleur
    void deleteColor(Long id);
}
