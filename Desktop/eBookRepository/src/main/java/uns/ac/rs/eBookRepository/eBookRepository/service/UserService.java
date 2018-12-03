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
}
