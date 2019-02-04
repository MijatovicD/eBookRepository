package uns.ac.rs.eBookRepository.eBookRepository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uns.ac.rs.eBookRepository.eBookRepository.entity.User;
import uns.ac.rs.eBookRepository.eBookRepository.repository.UserRepository;

import java.util.List;

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    UserRepository userRepository;

    @Override
    public User findOne(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void remove(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if(user.getUsername().equals(username))
            return user;
        else
            return null;
    }
}
