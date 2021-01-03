package ie.project.RegistrationAndLogin.Folders;

import java.util.ArrayList;
import java.util.List;

public class Folder {
    public String name;
    public static List<String> foldernames=new ArrayList<>();

    public Folder() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        foldernames.add(name);
        this.name = name;
    }
    public  List<String> foldernames()
    {
        return foldernames;
    }
}
