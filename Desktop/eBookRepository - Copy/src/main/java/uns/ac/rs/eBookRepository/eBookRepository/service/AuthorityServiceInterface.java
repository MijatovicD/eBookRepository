package uns.ac.rs.eBookRepository.eBookRepository.service;

import uns.ac.rs.eBookRepository.eBookRepository.model.Authority;

public interface AuthorityServiceInterface {

    Authority findByName(String name);

}

