package uns.ac.rs.eBookRepository.eBookRepository.lucene.model;

import org.springframework.web.multipart.MultipartFile;
import uns.ac.rs.eBookRepository.eBookRepository.entity.Category;
import uns.ac.rs.eBookRepository.eBookRepository.entity.Language;

import java.util.Arrays;

public class UploadModel {

    private String title;

    private String keywords;

    private String author;
    private Integer publicationYear;
    private String MIME;
    private MultipartFile[] files;
    private Category category;
    private Language language;

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

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "UploadModel{" +
                "title='" + title + '\'' +
                ", keywords='" + keywords + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", MIME='" + MIME + '\'' +
                ", files=" + Arrays.toString(files) +
                ", category=" + category +
                ", language=" + language +
                '}';
    }

}
