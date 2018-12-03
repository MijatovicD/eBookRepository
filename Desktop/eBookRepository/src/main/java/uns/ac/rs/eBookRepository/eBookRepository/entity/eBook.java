package uns.ac.rs.eBookRepository.eBookRepository.entity;

import javax.persistence.*;

@Entity
@Table(name = "eBooks")
public class eBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eBook_id", unique=true, nullable=false)
    private int eBook_id;

    @Column(name = "title", unique=false, nullable=false)
    private String title;

    @Column(name = "author", unique=false, nullable=false)
    private String author;

    @Column(name = "keywords", unique=false, nullable=false)
    private String keywords;

    @Column(name = "publication_year", unique=false, nullable=false)
    private int publicationYear;

    @Column(name = "filename", unique=false, nullable=false)
    private String filename;

    @Column(name = "mime", unique=false, nullable=false)
    private String MIME;

    @ManyToOne
    @JoinColumn(name="language_id", referencedColumnName="language_id", nullable=false)
    private Language language;

    @ManyToOne
    @JoinColumn(name="category_id", referencedColumnName="category_id", nullable=false)
    private Category category_id;

    public eBook(){

    }

    public eBook(int eBook_id, String title, String author, String keywords, int publicationYear, String filename, String MIME, Language language, Category category_id) {
        this.eBook_id = eBook_id;
        this.title = title;
        this.author = author;
        this.keywords = keywords;
        this.publicationYear = publicationYear;
        this.filename = filename;
        this.MIME = MIME;
        this.language = language;
        this.category_id = category_id;
    }

    public int geteBook_id() {
        return eBook_id;
    }

    public void seteBook_id(int eBook_id) {
        this.eBook_id = eBook_id;
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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category_id) {
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return "eBook{" +
                "eBook_id=" + eBook_id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", keywords='" + keywords + '\'' +
                ", publicationYear=" + publicationYear +
                ", filename='" + filename + '\'' +
                ", MIME='" + MIME + '\'' +
                ", language=" + language +
                ", category_id=" + category_id +
                '}';
    }
}
