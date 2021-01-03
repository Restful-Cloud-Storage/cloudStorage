package ie.project.RegistrationAndLogin.web.dto;

import ie.project.RegistrationAndLogin.Files.getUrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;

@Controller
public class MainController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String home(){

        File file = new File(getUrl.path);

        // true if the directory was created, false otherwise
        if (file.mkdirs()) {
            System.out.println("Directory is created!");
        } else {
            System.out.println("Failed to create directory!");
        }

        File root = new File(getUrl.path+"root");
        if (root.mkdirs()) {
            System.out.println("Root Directory is created!");
        } else {
            System.out.println("Root Failed to create directory!");
        }

        return "upload_file";
    }

}
