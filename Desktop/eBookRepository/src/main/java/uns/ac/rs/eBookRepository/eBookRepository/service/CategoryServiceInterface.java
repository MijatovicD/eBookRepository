package uns.ac.rs.eBookRepository.eBookRepository.service;

import uns.ac.rs.eBookRepository.eBookRepository.entity.Category;

import java.util.List;

public interface CategoryServiceInterface {

    Category findOne(Integer id);

    List<Category> findAll();

    Category save(Category category);
}
