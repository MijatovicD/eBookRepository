package uns.ac.rs.eBookRepository.eBookRepository.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "eBooks")
public class eBook implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique=true, nullable=false)
    private int id;

    @Column(name = "title", unique=false, nullable=false)
    private String title;

    @Column(name = "author", unique=false, nullable=false)
    private String author;

    @Column(name = "keywords", unique=false, nullable=false)
    private String keywords;

    @Column(name = "publication_year", unique=false, nullable=false)
    private int publicationYear;

    @Column(name = "filename", unique=false, nullable=true)
    private String filename;

    @Column(name = "mime", unique=false, nullable=false)
    private String MIME;

    @ManyToOne
    @JoinColumn(name="language_id", referencedColumnName="language_id", nullable=true)
    private Language language_id;

    @ManyToOne
    @JoinColumn(name="category_id", referencedColumnName="category_id", nullable=false)
    private Category category_id;


    public eBook(){

    }

    public eBook(int id, String title, String author, String keywords, int publicationYear, String filename, String MIME, Language language_id, Category category_id) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.keywords = keywords;
        this.publicationYear = publicationYear;
        this.filename = filename;
        this.MIME = MIME;
        this.language_id = language_id;
        this.category_id = category_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getMIME() {
        return MIME;
    }

    public void setMIME(String MIME) {
        this.MIME = MIME;
    }


    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category_id) {
        this.category_id = category_id;
    }

    public Language getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(Language language_id) {
        this.language_id = language_id;
    }

    @Override
    public String toString() {
        return "eBook{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", keywords='" + keywords + '\'' +
                ", publicationYear=" + publicationYear +
                ", filename='" + filename + '\'' +
                ", MIME='" + MIME + '\'' +
                ", category_id=" + category_id +
                ", language_id=" + language_id +
                '}';
    }
}
