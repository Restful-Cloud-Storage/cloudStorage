package ie.project.RegistrationAndLogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@SpringBootApplication
public class CloudStorageApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(CloudStorageApplication.class, args);
	}

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("get-data");
	}

}
