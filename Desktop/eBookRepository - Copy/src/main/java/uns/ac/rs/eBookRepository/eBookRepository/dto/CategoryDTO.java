package uns.ac.rs.eBookRepository.eBookRepository.dto;

import uns.ac.rs.eBookRepository.eBookRepository.entity.Category;

import java.io.Serializable;

public class CategoryDTO implements Serializable {

    private int id;
    private String name;

    public CategoryDTO(){

    }

    public CategoryDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public  CategoryDTO(Category category){
        this(category.getCategory_id(), category.getName());
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
