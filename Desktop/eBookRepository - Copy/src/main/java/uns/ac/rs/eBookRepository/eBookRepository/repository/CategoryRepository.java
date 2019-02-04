package uns.ac.rs.eBookRepository.eBookRepository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uns.ac.rs.eBookRepository.eBookRepository.entity.Category;
import uns.ac.rs.eBookRepository.eBookRepository.entity.User;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByName(String name);

    List<Category> findByUsers(User user);
}
