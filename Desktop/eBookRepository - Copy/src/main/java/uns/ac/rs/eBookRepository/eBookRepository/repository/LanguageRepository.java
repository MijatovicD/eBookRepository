package uns.ac.rs.eBookRepository.eBookRepository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uns.ac.rs.eBookRepository.eBookRepository.entity.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
