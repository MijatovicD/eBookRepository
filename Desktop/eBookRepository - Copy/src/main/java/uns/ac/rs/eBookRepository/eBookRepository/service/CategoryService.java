package uns.ac.rs.eBookRepository.eBookRepository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uns.ac.rs.eBookRepository.eBookRepository.entity.Category;
import uns.ac.rs.eBookRepository.eBookRepository.entity.User;
import uns.ac.rs.eBookRepository.eBookRepository.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService implements CategoryServiceInterface {

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public Category findOne(Integer id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void remove(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category findByName(String name) {
        Category category = categoryRepository.findByName(name);
        if(category.getName().equals(name))
            return category;
        else
            return null;
    }

    @Override
    public List<Category> findByUser(User user) {
        return categoryRepository.findByUsers(user);
    }
}
