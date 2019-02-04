package uns.ac.rs.eBookRepository.eBookRepository.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uns.ac.rs.eBookRepository.eBookRepository.entity.Category;
import uns.ac.rs.eBookRepository.eBookRepository.entity.eBook;
import uns.ac.rs.eBookRepository.eBookRepository.lucene.Indexer;
import uns.ac.rs.eBookRepository.eBookRepository.lucene.model.IndexUnit;
import uns.ac.rs.eBookRepository.eBookRepository.lucene.model.UploadModel;
import uns.ac.rs.eBookRepository.eBookRepository.service.CategoryServiceInterface;
import uns.ac.rs.eBookRepository.eBookRepository.service.eBookService;
import uns.ac.rs.eBookRepository.eBookRepository.service.eBookServiceInterface;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin("*")
public class IndexerController {

    private static String DATA_DIR_PATH;

    @Autowired
    CategoryServiceInterface categoryService;

    @Autowired
    eBookServiceInterface eBookService;

    static {
        ResourceBundle rb=ResourceBundle.getBundle("application");
        DATA_DIR_PATH=rb.getString("dataDir");
    }

    @GetMapping("/reindex")
    public ResponseEntity<String> index() throws IOException {
        File dataDir = getResourceFilePath(DATA_DIR_PATH);
        long start = new Date().getTime();
        int numIndexed = Indexer.getInstance().index(dataDir);
        long end = new Date().getTime();
        String text = "Indexing " + numIndexed + " files took "
                + (end - start) + " milliseconds";
        return new ResponseEntity<String>(text, HttpStatus.OK);
    }

    private File getResourceFilePath(String path) {
        URL url = this.getClass().getClassLoader().getResource(path);
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        }
        return file;
    }



    @PostMapping(value="/index/add")
    public ResponseEntity<String> multiUploadFileModel(@ModelAttribute UploadModel model) {


        try {

            indexUploadedFile(model);

        } catch (IOException e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("Successfully uploaded!", HttpStatus.OK);

    }


    //save file
    private String saveUploadedFile(MultipartFile file) throws IOException {
        String retVal = null;
        if (! file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(getResourceFilePath(DATA_DIR_PATH).getAbsolutePath() + File.separator + file.getOriginalFilename());
            Files.write(path, bytes);
            retVal = path.toString();
        }
        return retVal;
    }

    private void indexUploadedFile(UploadModel model) throws IOException{

        for (MultipartFile file : model.getFiles()) {

            if (file.isEmpty()) {
                continue; //next please
            }
            String fileName = saveUploadedFile(file);
            if(fileName != null){
                IndexUnit indexUnit = Indexer.getInstance().getHandler(fileName).getIndexUnit(new File(fileName));
                indexUnit.setTitle(model.getTitle());
                indexUnit.setAuthor(model.getAuthor());
                System.out.println("AUTOOOOOOOOR" + model.getAuthor());
                indexUnit.setKeywords(new ArrayList<String>(Arrays.asList(model.getKeywords().split(" "))));
                indexUnit.setPublicationYear(model.getPublicationYear());
                indexUnit.setMIME(model.getMIME());
                //Integer name = model.getCategory().getCategory_id();
                //Object o = name;
                //indexUnit.setCategory((Category) o);
                indexUnit.setCategory(model.getCategory());
                indexUnit.setLanguage(model.getLanguage());
                Indexer.getInstance().add(indexUnit.getLuceneDocument());

                eBook eBook = new eBook();

                eBook.setTitle(model.getTitle());
                eBook.setAuthor(model.getAuthor());
                eBook.setKeywords(model.getKeywords());
                eBook.setPublicationYear(model.getPublicationYear());
                eBook.setMIME(model.getMIME());
                eBook.setLanguage_id(model.getLanguage());
                eBook.setCategory_id(model.getCategory());

                eBook = eBookService.save(eBook);
            }
        }
    }


}

