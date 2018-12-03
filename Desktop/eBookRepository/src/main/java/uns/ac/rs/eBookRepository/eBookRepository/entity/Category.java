package uns.ac.rs.eBookRepository.eBookRepository.entity;

import javax.persistence.*;

@Entity
@Table(name = "categorys")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", unique=true, nullable=false)
    private int category_id;

    @Column(name = "name", unique=false, nullable=false)
    private String name;

    public Category(){

    }

    public Category(int category_id, String name) {
        this.category_id = category_id;
        this.name = name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", name='" + name + '\'' +
                '}';
    }
}
