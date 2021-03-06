package uns.ac.rs.eBookRepository.eBookRepository.dto;

import uns.ac.rs.eBookRepository.eBookRepository.entity.eBook;

import java.io.Serializable;

public class eBookDTO implements Serializable {

    private int id;
    private String title;
    private String author;
    private String keywords;
    private int publicationYear;
    private String filename;
    private String MIME;

    public eBookDTO(int id, String title, String author, String keywords, int publicationYear, String filename, String MIME) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.keywords = keywords;
        this.publicationYear = publicationYear;
        this.filename = filename;
        this.MIME = MIME;
    }

    public eBookDTO(eBook eBook){
        this(eBook.geteBook_id(), eBook.getTitle(), eBook.getAuthor(), eBook.getKeywords(), eBook.getPublicationYear(), eBook.getFilename(), eBook.getMIME());
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
}
