package uns.ac.rs.eBookRepository.eBookRepository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uns.ac.rs.eBookRepository.eBookRepository.entity.eBook;
import uns.ac.rs.eBookRepository.eBookRepository.repository.EBookRepository;

import java.util.List;

@Service
public class eBookService implements eBookServiceInterface{

    @Autowired
    EBookRepository eBookRepository;

    @Override
    public eBook findOne(Integer id) {
        return eBookRepository.findById(id).get();
    }

    @Override
    public List<eBook> findAll() {
        return eBookRepository.findAll();
    }
}
