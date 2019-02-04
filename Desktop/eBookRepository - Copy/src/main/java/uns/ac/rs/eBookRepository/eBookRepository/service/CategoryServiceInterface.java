package uns.ac.rs.eBookRepository.eBookRepository.service;

import uns.ac.rs.eBookRepository.eBookRepository.entity.Category;
import uns.ac.rs.eBookRepository.eBookRepository.entity.User;

import java.util.List;

public interface CategoryServiceInterface {

    Category findOne(Integer id);

    List<Category> findAll();

    Category save(Category category);

    void remove(Integer id);

    Category findByName(String name);

    List<Category> findByUser(User user);
}
