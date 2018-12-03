package uns.ac.rs.eBookRepository.eBookRepository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uns.ac.rs.eBookRepository.eBookRepository.entity.Language;
import uns.ac.rs.eBookRepository.eBookRepository.repository.LanguageRepository;

import java.util.List;

@Service
public class LanguageService implements LanguageServiceInterface{


    @Autowired
    LanguageRepository languageRepository;

    @Override
    public Language findOne(Integer id) {
        return languageRepository.findById(id).get();
    }

    @Override
    public List<Language> findAll() {
        return languageRepository.findAll();
    }
}
