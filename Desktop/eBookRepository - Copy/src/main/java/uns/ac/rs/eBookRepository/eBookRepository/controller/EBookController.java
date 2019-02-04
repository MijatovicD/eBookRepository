package uns.ac.rs.eBookRepository.eBookRepository.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uns.ac.rs.eBookRepository.eBookRepository.dto.eBookDTO;
import uns.ac.rs.eBookRepository.eBookRepository.entity.eBook;
import uns.ac.rs.eBookRepository.eBookRepository.service.eBookServiceInterface;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/ebooks")
@CrossOrigin("*")
public class EBookController {

    @Autowired
    eBookServiceInterface eBookService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<eBookDTO>> getBooks(){
        List<eBook> eBooks = eBookService.findAll();
        List<eBookDTO> eBookDTO = new ArrayList<>();
        for (eBook books : eBooks){
            eBookDTO.add(new eBookDTO(books));
        }

        return new ResponseEntity<List<eBookDTO>>(eBookDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<eBookDTO> getBook(@PathVariable("id") Integer id){
        eBook eBook = eBookService.findOne(id);
        if (eBook == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new eBookDTO(eBook), HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<eBookDTO> addCategory(@RequestBody eBookDTO eBookDTO){
        eBook eBook = new eBook();

        eBook.setTitle(eBookDTO.getTitle());
        eBook.setAuthor(eBookDTO.getAuthor());
        eBook.setKeywords(eBookDTO.getKeywords());
        eBook.setPublicationYear(eBookDTO.getPublicationYear());
        eBook.setFilename(eBookDTO.getFilename());
        eBook.setMIME(eBookDTO.getMIME());

        eBook = eBookService.save(eBook);

        return new ResponseEntity<eBookDTO>(new eBookDTO(eBook), HttpStatus.CREATED);
    }
}
