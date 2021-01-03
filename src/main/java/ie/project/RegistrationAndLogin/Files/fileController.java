package ie.project.RegistrationAndLogin.Files;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class fileController {

//        @RequestMapping(value = "/files")
////        public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
////            model.addAttribute("name", name);
//    public static String welcome() {
//            return "files";
//        }

    @GetMapping("/files")
    String files(Model model){
    getFileName GF = new getFileName();
    List<String> filesname = GF.getFilesName(getUrl.path + "root");
//        List<String> filesname = files.filesNames();

        System.out.println(filesname);
        model.addAttribute("files", filesname);
        return "files";
}


    @RequestMapping(value ="/files/{folderName}")
    public String iteration(@PathVariable("folderName") String folderName, Model model) {
//folderName = "hast";
//        files.setName("a.jpg");
//        files.setName("b.jpg");
//        files.setName("th");

        getUrl url = new getUrl();
        url.setCurrentFolder(folderName);


        getFileName GF = new getFileName();
        List<String> filesname = GF.getFilesName(getUrl.path + folderName);
//        List<String> filesname = files.filesNames();

        System.out.println(filesname);
        model.addAttribute("files", filesname);
        return "files";
    }

    @PostMapping(path = "/files")
    String files(@RequestParam String fname , Model model){
            Files file = new Files();
            file.setFileName(fname);
        System.out.println("fname : " + fname);
        List<String> files= file.filesNames();
        model.addAttribute("files",files);
        return "files";
    }

}
