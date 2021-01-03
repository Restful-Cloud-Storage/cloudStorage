package ie.project.RegistrationAndLogin.uploadFile;

import ie.project.RegistrationAndLogin.Files.Files;
import ie.project.RegistrationAndLogin.Files.getFileName;
import ie.project.RegistrationAndLogin.Files.getUrl;
import ie.project.RegistrationAndLogin.model.User;
import ie.project.RegistrationAndLogin.repository.UserRepository;
import ie.project.RegistrationAndLogin.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


@Controller
/**
 * @Controller annotation is also a specialization of @Component annotation.
 * It makes a class accept the HTTP request and respond accordingly.
 * It also takes care of the various conversion of request payload to
 * an internal data structure.
 */
public class uploadFileController {
    public String lname = "root";
    //    UserRepository userRepository ;
//    String email="r.mirzayi.1999@gmail.com";
    User user;
    @Autowired
    /**
     *  @Autowired the FileService bean so that we can use its uploadFile method.
     */
            FileService fileService;

    @GetMapping("/upload_file")
//    @GetMapping("/download/{fileName:.+}")
    /**
     * GetMapping at / which will simply return String upload. Being a controller
     * class Spring will search for upload.html and serve it to the browser.
     */
//    public String index() {
//        return "upload";
//    }
    public String index() {
        return "upload_file";
    }

    @PostMapping("/cloudStorage")
    /**
     * PostMapping of /uploadFile, which have a RequestParam of MultipartFile which is an object that has our file and its metadata details.
     */
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        /**
         * used the FileService uploadFile method to upload the file.
         */
        fileService.uploadFile(file);
        /**
         *  RedirectAttributes is a specialization of Spring Model interface that is use to select attributes for a redirect scenario.
         */
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:upload_file";
    }
    @PostMapping("/uploadFiles")
    public String uploadFiles(@RequestParam("files") MultipartFile[] files, RedirectAttributes redirectAttributes) {

        getFileName GF = new getFileName();
        List<String> folders = GF.getFilesName(getUrl.path);


        System.out.println("output : "+folders.contains(lname));
        if(folders.contains(lname)) {
            System.out.println("name : " + lname);
            String geturl = getUrl.path + lname;
            fileService.setUploadDir(geturl);
        }
        user = UserServiceImpl.userRepository.findByEmail(getUrl.email);
        Files filename = new Files();
        Arrays.asList(files)
                .stream()
                .forEach(file -> fileService.uploadFile(file));
        System.out.println(files.length);
        int uploadSize=user.getUploadSize()/1048576/**in MB*/;
        for (MultipartFile file:files) {

            uploadSize+=(file.getSize()/1048576);
            System.out.println(file.getOriginalFilename()+" size:   " +file.getSize()/1048576);
        }
        user.setUploadSize(uploadSize);
        UserServiceImpl.userRepository.save(user);

//        Arrays.asList(files)
//                .stream()
//                .forEach(file -> filename.setFileName(file.getOriginalFilename()));
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded all files!");

        return "redirect:upload_file";
    }

    @RequestMapping(value = "/upload_file_reset", method = RequestMethod.GET)
    public String redirectUploadFile() {
        return "upload_file";
    }

    @PostMapping(path = "/uploadfolders")
    String folderfind(@RequestParam String lname, Model model) {
//        Folder folder = new Folder();
//        folder.setName(lname);
//        List<String> folders =folder.foldernames();
//        model.addAttribute("folders", folders);
        this.lname = lname;
        return "upload_file";

    }
    @RequestMapping("/pie")
    public String generatePieChart(Model model) {
        double totalSpace=10240;/**MB*/
        System.out.println("total space : "+totalSpace);
        user = UserServiceImpl.userRepository.findByEmail(getUrl.email);
        float uploadSize=user.getUploadSize();/**MB*/
        System.out.println("filesizeee : "+uploadSize);
        model.addAttribute("upload", uploadSize);
        double freeSpace=totalSpace-uploadSize;
        System.out.println("freespace : "+freeSpace);
        model.addAttribute("free", freeSpace);

        return "get-data";
    }

}