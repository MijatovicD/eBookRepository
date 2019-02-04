package uns.ac.rs.eBookRepository.eBookRepository.service;

import uns.ac.rs.eBookRepository.eBookRepository.entity.User;

import java.util.List;

public interface UserServiceInterface {

    User findOne(Integer id);

    List<User> findAll();

    User save(User user);

    void remove(Integer id);

    User findByUsername(String username);
}
