package ie.project.RegistrationAndLogin.Folders;

import ie.project.RegistrationAndLogin.Files.getFileName;
import ie.project.RegistrationAndLogin.Files.getUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.List;

@Controller
public class folderController {

    public static String CurrentFolder ;

    @Autowired
    /**
     *  @Autowired the FileService bean so that we can use its uploadFile method.
     */
    @RequestMapping(value = "/folders")
    public String folders() {
        return "folders";
    }

    @GetMapping("folders")
    public String iteration(Model model) {
        String dir = getUrl.path;

//        Folder folder=new Folder();
//        folder.setName("IR");
//        folder.setName("Internet");
//        folder.setName("DataMinig");
//        List<String> folders =folder.foldernames();
        getFileName GF = new getFileName();
        List<String> folders = GF.getFilesName(dir);
        model.addAttribute("folders", folders);
        return "folders";
    }
    @PostMapping(path = "/folders")
    String folder(@RequestParam String lname, Model model) {

        String dir = getUrl.path;

        File file = new File(dir+ lname);

        // true if the directory was created, false otherwise
        if (file.mkdirs()) {
            System.out.println("Directory is created!");
        } else {
            System.out.println("Failed to create directory!");
        }
        getFileName GF = new getFileName();
        List<String> folders = GF.getFilesName(dir);

//        Folder folder = new Folder();
//        folder.setName(lname);
        System.out.println("name : "+lname);
//        List<String> folders =folder.foldernames();
        model.addAttribute("folders", folders);
        return "folders";
    }
    @RequestMapping(value = "/create_folder")
    public String createFolder() {
        return "create_folder";
    }
}
