package uns.ac.rs.eBookRepository.eBookRepository.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uns.ac.rs.eBookRepository.eBookRepository.model.Authority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique=true, nullable=false)
    private Integer user_id;

    @Column(name = "firstname", unique=false, nullable=false)
    private String firstname;

    @Column(name = "lastname", unique=false, nullable=false)
    private String lastname;

    @Column(name = "username", unique=true, nullable=false)
    private String username;

    @Column(name = "password", unique=false, nullable=false)
    private String password;

    @Column(name = "type", unique=false, nullable=false)
    private String type;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "user_categories",joinColumns = { @JoinColumn(name = "user_id") },inverseJoinColumns = { @JoinColumn(name = "category_id") })
    private Set<Category> categories = new HashSet<Category>();

    @ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name="user_authority",
			joinColumns=@JoinColumn(name="user_id",referencedColumnName="user_id"),
			inverseJoinColumns = @JoinColumn(name="authority_id",referencedColumnName="authority_id"))
	private Set<Authority> user_authorities = new HashSet<>();

    public User(){

    }

    public User(Integer user_id, String firstname, String lastname, String username, String password, String type, Set<Category> categories) {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.type = type;
        this.categories = categories;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.user_authorities;
    }

    public Set<Authority> getUser_authorities() {
        return user_authorities;
    }

    public void setUser_authorities(Set<Authority> user_authorities) {
        this.user_authorities = user_authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String user_passwsord) {
        this.password = user_passwsord;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", categories=" + categories +
                '}';
    }


}
