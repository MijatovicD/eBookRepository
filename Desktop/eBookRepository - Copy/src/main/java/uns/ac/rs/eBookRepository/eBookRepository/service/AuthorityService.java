package uns.ac.rs.eBookRepository.eBookRepository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uns.ac.rs.eBookRepository.eBookRepository.repository.AuthorityRepository;
import uns.ac.rs.eBookRepository.eBookRepository.model.Authority;

@Service
public class AuthorityService implements AuthorityServiceInterface{

    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public Authority findByName(String name) {
        return authorityRepository.findByName(name);
    }

}
