package uns.ac.rs.eBookRepository.eBookRepository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uns.ac.rs.eBookRepository.eBookRepository.entity.eBook;

public interface EBookRepository extends JpaRepository<eBook, Integer> {
}
