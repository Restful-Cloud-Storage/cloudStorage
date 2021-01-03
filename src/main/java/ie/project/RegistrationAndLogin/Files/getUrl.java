package ie.project.RegistrationAndLogin.Files;

import ie.project.RegistrationAndLogin.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.File;

public class getUrl {

    public static String email=getUsername();
    public static String path= "E:/" +email+ "/";
    public static String CurrentFolder;
    private static String url;
    public String UserName;

    public static String getUsername(){
        Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
        } else {
             username = principal.toString();
        }
        return username;
    }


    public String getCurrentFolder() {
        return CurrentFolder;
    }

    public void setCurrentFolder(String currentFolder) {
        CurrentFolder = currentFolder;
        if(currentFolder == "folders.html") CurrentFolder="root";
        url = path+CurrentFolder+"/";
        System.out.println(url);
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUrlPath(){
        System.out.println("getUrl : "+url);
        return url;
    }
}
