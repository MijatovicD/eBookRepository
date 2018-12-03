package uns.ac.rs.eBookRepository.eBookRepository.service;

import uns.ac.rs.eBookRepository.eBookRepository.entity.eBook;

import java.util.List;

public interface eBookServiceInterface {

    eBook findOne(Integer id);

    List<eBook> findAll();
}
