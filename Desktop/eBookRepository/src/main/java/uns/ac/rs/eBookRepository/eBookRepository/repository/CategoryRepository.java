package uns.ac.rs.eBookRepository.eBookRepository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uns.ac.rs.eBookRepository.eBookRepository.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
