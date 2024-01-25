package soa.services;

import soa.entities.Title;

import java.util.Map;
import java.util.List;

public interface TitleService {

    Title createTitle(Title title);

    Title getTitleById(Long id);

    Title getTitleByName(String name);

    Map<String, Object> someTitleOperation();

    List<Title> getAllTitles();

    Title updateTitle(Long id, Title titleUpdate);

    void deleteTitle(Long id);

    long countTitles();
}
