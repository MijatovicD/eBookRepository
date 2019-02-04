package uns.ac.rs.eBookRepository.eBookRepository.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", unique=true, nullable=false)
    private int category_id;

    @Column(name = "name", unique=false, nullable=false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "categories")
    private Set<User> users = new HashSet<User>();

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
