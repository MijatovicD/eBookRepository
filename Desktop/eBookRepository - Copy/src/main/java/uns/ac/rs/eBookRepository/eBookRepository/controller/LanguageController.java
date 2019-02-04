package uns.ac.rs.eBookRepository.eBookRepository.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uns.ac.rs.eBookRepository.eBookRepository.dto.LanguageDTO;
import uns.ac.rs.eBookRepository.eBookRepository.entity.Language;
import uns.ac.rs.eBookRepository.eBookRepository.service.LanguageServiceInterface;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/languages")
public class LanguageController {

    @Autowired
    LanguageServiceInterface languageService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<LanguageDTO>> getLanguages(){
        List<Language> languages = languageService.findAll();
        List<LanguageDTO> languageDTO = new ArrayList<LanguageDTO>();
        for (Language l : languages) {
            languageDTO.add(new LanguageDTO(l));
        }
        return new ResponseEntity<List<LanguageDTO>>(languageDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LanguageDTO> getLanguage(@PathVariable("id") Integer id){
        Language language = languageService.findOne(id);
        if (language != null){
            return new ResponseEntity<LanguageDTO>(new LanguageDTO(language), HttpStatus.OK);
        }
        return new ResponseEntity<LanguageDTO>(HttpStatus.NOT_FOUND);
    }
}
