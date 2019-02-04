package uns.ac.rs.eBookRepository.eBookRepository.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uns.ac.rs.eBookRepository.eBookRepository.dto.UserDTO;
import uns.ac.rs.eBookRepository.eBookRepository.entity.Category;
import uns.ac.rs.eBookRepository.eBookRepository.entity.User;
import uns.ac.rs.eBookRepository.eBookRepository.service.CategoryServiceInterface;
import uns.ac.rs.eBookRepository.eBookRepository.service.UserServiceInterface;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserServiceInterface userService;

    @Autowired
    CategoryServiceInterface categoryService;

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<User> users = userService.findAll();
        List<UserDTO> userDTO = new ArrayList<UserDTO>();
        for (User u : users){
            userDTO.add(new UserDTO(u));
        }
        return new ResponseEntity<List<UserDTO>>(userDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Integer id){
        User user = userService.findOne(id);
        if (user != null){
            return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
        }
        return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO){
        User user = new User();

        user.setUser_id(userDTO.getId());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setUsername(userDTO.getUsername());
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        user.setPassword(bc.encode(userDTO.getPassword()));
        user.setType(userDTO.getType());

        user = userService.save(user);

        return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable("id") Integer id){
        User user = userService.findOne(id);

        if (user == null){
            return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
        }

        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setUsername(userDTO.getUsername());
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        user.setPassword(bc.encode(userDTO.getPassword()));
        user.setType(userDTO.getType());

        user = userService.save(user);

        return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
    }

    @GetMapping(value="/username/{username}")
    public ResponseEntity<UserDTO> findByUsername(@PathVariable("username") String username){
        User user = userService.findByUsername(username);
        if(user != null) {
            return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
        }
        return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/log/{username}/{password}")
    public ResponseEntity<UserDTO> login(@PathVariable("username") String username, @PathVariable("password") String password){
        User user = userService.findByUsername(username);
        System.out.println("USERNAMEEE" + user);
        if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
            return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
        }
        return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value="/addCategory/{userId}/{categoryId}")
    public ResponseEntity<UserDTO> addCategoryInUser(@PathVariable("userId") int postId,@PathVariable("categoryId") int tagId){
        User user = userService.findOne(postId);
        Category category = categoryService.findOne(tagId);

        if(user == null || category == null) {
            return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
        }

        user.getCategories().add(category);
        category.getUsers().add(user);

        user = userService.save(user);
        category = categoryService.save(category);
        return new ResponseEntity<UserDTO>(new UserDTO(user),HttpStatus.OK);
    }


    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        User user = userService.findOne(id);
        if (user == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        userService.remove(id);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
