package uns.ac.rs.eBookRepository.eBookRepository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uns.ac.rs.eBookRepository.eBookRepository.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{

    Authority findByName(String name);
}

