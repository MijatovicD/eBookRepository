package uns.ac.rs.eBookRepository.eBookRepository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uns.ac.rs.eBookRepository.eBookRepository.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
