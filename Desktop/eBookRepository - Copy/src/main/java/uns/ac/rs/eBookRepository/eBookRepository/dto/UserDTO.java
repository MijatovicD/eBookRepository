package uns.ac.rs.eBookRepository.eBookRepository.dto;

import uns.ac.rs.eBookRepository.eBookRepository.entity.User;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private Integer id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String type;

    public UserDTO(){

    }

    public UserDTO(Integer id, String firstname, String lastname, String username, String password, String type) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public UserDTO(User user){
        this(user.getUser_id(), user.getFirstname(), user.getLastname(), user.getUsername(), user.getPassword(), user.getType());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
