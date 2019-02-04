package uns.ac.rs.eBookRepository.eBookRepository.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "languages")
public class Language implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id", unique=true, nullable=false)
    private int language_id;

    @Column(name = "name", unique=false, nullable=false)
    private String name;

    public Language(){

    }

    public Language(int language_id, String name) {
        this.language_id = language_id;
        this.name = name;
    }

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = language_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Language{" +
                "language_id=" + language_id +
                ", name='" + name + '\'' +
                '}';
    }
}
