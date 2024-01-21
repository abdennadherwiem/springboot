package soa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soa.entities.Color;
import soa.repository.ColorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<String> getAllColors() {
        List<Color> allColors = colorRepository.findAll();
        return allColors.stream()
                .map(Color::getName)
                .collect(Collectors.toList());
    }

    @Override
    public String addColor(String colorName) {
        // Ajouter une couleur si elle n'existe pas déjà
        if (colorRepository.findColorByNameContaining(colorName).isEmpty()) {
            Color newColor = new Color();
            newColor.setName(colorName);
            colorRepository.save(newColor);
            return "Couleur ajoutée avec succès.";
        } else {
            return "La couleur existe déjà.";
        }
    }

    @Override
    public String updateColor(Long id, String updatedColorName) {
        Color existingColor = colorRepository.findById(id).orElse(null);

        if (existingColor != null) {
            existingColor.setName(updatedColorName);
            colorRepository.save(existingColor);
            return "Couleur mise à jour avec succès.";
        } else {
            return "Couleur non trouvée.";
        }
    }

    @Override
    public void deleteColor(Long id) {
        colorRepository.deleteById(id);
    }
}
