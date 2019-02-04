package uns.ac.rs.eBookRepository.eBookRepository.dto;

import uns.ac.rs.eBookRepository.eBookRepository.entity.Language;

import java.io.Serializable;

public class LanguageDTO implements Serializable {

    private int id;
    private String name;

    public LanguageDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public LanguageDTO(Language language){
        this(language.getLanguage_id(), language.getName());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
