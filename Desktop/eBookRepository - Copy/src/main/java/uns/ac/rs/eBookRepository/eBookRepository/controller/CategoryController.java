package uns.ac.rs.eBookRepository.eBookRepository.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uns.ac.rs.eBookRepository.eBookRepository.dto.CategoryDTO;
import uns.ac.rs.eBookRepository.eBookRepository.entity.Category;
import uns.ac.rs.eBookRepository.eBookRepository.service.CategoryServiceInterface;
import uns.ac.rs.eBookRepository.eBookRepository.service.UserServiceInterface;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryController {

    @Autowired
    CategoryServiceInterface categoryService;

    @Autowired
    UserServiceInterface userService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<CategoryDTO>> getCategories(){
        List<Category> categories = categoryService.findAll();
        List<CategoryDTO> categoryDTO = new ArrayList<CategoryDTO>();
        for (Category c : categories) {
            categoryDTO.add(new CategoryDTO(c));
        }
        return new ResponseEntity<List<CategoryDTO>>(categoryDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") Integer id){
        Category category = categoryService.findOne(id);
        if (category != null){
            return new ResponseEntity<CategoryDTO>(new CategoryDTO(category), HttpStatus.OK);
        }
        return new ResponseEntity<CategoryDTO>(HttpStatus.NOT_FOUND);
    }


    @PostMapping(value = "/add")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO){
        Category category = new Category();
        category.setCategory_id(categoryDTO.getId());
        category.setName(categoryDTO.getName());

        category = categoryService.save(category);

        return new ResponseEntity<CategoryDTO>(new CategoryDTO(category), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}", consumes="application/json")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable("id") Integer id){
        Category category = categoryService.findOne(id);

        if (category == null){
            return new ResponseEntity<CategoryDTO>(HttpStatus.NOT_FOUND);
        }

        category.setName(categoryDTO.getName());

        category = categoryService.save(category);

        return new ResponseEntity<CategoryDTO>(new CategoryDTO(category), HttpStatus.OK);
    }


    @GetMapping(value = "category/{name}")
    public ResponseEntity<CategoryDTO> findByName(@PathVariable("name") String name){
        Category category = categoryService.findByName(name);
        if (category != null){
            return new ResponseEntity<CategoryDTO>(new CategoryDTO(category), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value="/user/{id}")
    public ResponseEntity<List<CategoryDTO>> getUserByCategory(@PathVariable("id") Integer id){
        List<Category> categories = categoryService.findByUser(userService.findOne(id));
        List<CategoryDTO> categoryDTO = new ArrayList<>();
        for(Category c : categories) {
            categoryDTO.add(new CategoryDTO(c));
        }
        return new ResponseEntity<List<CategoryDTO>>(categoryDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        Category category = categoryService.findOne(id);
        if (category == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.remove(id);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}