package uns.ac.rs.eBookRepository.eBookRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ResourceBundle;

@SpringBootApplication
public class EBookRepositoryApplication {

	private static String DATA_DIR_PATH;

	static {
		ResourceBundle rb = ResourceBundle.getBundle("application");
		DATA_DIR_PATH = rb.getString("dataDir");
	}

	public static void main(String[] args) {
		SpringApplication.run(EBookRepositoryApplication.class, args);
	}
}
