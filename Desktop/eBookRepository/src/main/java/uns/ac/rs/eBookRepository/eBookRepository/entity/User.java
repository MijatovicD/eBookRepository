package uns.ac.rs.eBookRepository.eBookRepository.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique=true, nullable=false)
    private int user_id;

    @Column(name = "firstname", unique=false, nullable=false)
    private String firstname;

    @Column(name = "lastname", unique=false, nullable=false)
    private String lastname;

    @Column(name = "username", unique=false, nullable=false)
    private String username;

    @Column(name = "password", unique=false, nullable=false)
    private String user_passwsord;

    @Column(name = "type", unique=false, nullable=false)
    private String type;

    public User(){

    }

    public User(int user_id, String firstname, String lastname, String username, String user_passwsord, String type) {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.user_passwsord = user_passwsord;
        this.type = type;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_passwsord() {
        return user_passwsord;
    }

    public void setUser_passwsord(String user_passwsord) {
        this.user_passwsord = user_passwsord;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", user_passwsord='" + user_passwsord + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
