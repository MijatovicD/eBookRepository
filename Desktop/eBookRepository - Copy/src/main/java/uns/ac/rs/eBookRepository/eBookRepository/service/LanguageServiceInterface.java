package uns.ac.rs.eBookRepository.eBookRepository.service;

import uns.ac.rs.eBookRepository.eBookRepository.entity.Language;

import java.util.List;

public interface LanguageServiceInterface {

    Language findOne(Integer id);

    List<Language> findAll();
}
