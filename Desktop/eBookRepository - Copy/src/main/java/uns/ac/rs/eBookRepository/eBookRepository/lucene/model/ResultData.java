package uns.ac.rs.eBookRepository.eBookRepository.lucene.model;

import uns.ac.rs.eBookRepository.eBookRepository.entity.Category;
import uns.ac.rs.eBookRepository.eBookRepository.entity.Language;

public class ResultData {

    private String title;
    private String keywords;
    private String author;
    private Integer publicationYear;
    private String MIME;
    private String location;
    private String highlight;
    private Category category;
    private Language language;

    public ResultData() {
        super();
    }

    public ResultData(String title, String keywords, String author, Integer publicationYear, String MIME, String location, String highlight) {
        this.title = title;
        this.keywords = keywords;
        this.author = author;
        this.publicationYear = publicationYear;
        this.MIME = MIME;
        this.location = location;
        this.highlight = highlight;
    }

    public ResultData(String title, String keywords, String author, String MIME, String location, String highlight, Category category, Language language) {
        this.title = title;
        this.keywords = keywords;
        this.author = author;
        this.MIME = MIME;
        this.location = location;
        this.highlight = highlight;
        this.category = category;
        this.language = language;
    }

    public ResultData(String title, String keywords, String author, String MIME, String location, String highlight) {
        this.title = title;
        this.keywords = keywords;
        this.author = author;
        this.MIME = MIME;
        this.location = location;
        this.highlight = highlight;
    }
/*
    public ResultData(String title, String keywords, String location, String highlight) {
        super();
        this.title = title;
        this.keywords = keywords;
        this.location = location;
        this.highlight = highlight;
    }
*/
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getMIME() {
        return MIME;
    }

    public void setMIME(String MIME) {
        this.MIME = MIME;
    }
}
