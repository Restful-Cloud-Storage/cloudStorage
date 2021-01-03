package ie.project.RegistrationAndLogin.Files;

import java.util.ArrayList;
import java.util.List;

public class Files {

    public static List<String> filesList = new ArrayList<>();
    public static String name;

    public Files() {
        this.name = name;
    }

    public static List<String> filesNames() {
        return filesList;
    }

    public static String getName() {
        return name;
    }

    public static void setFileName(String name) {
        filesList.add(name);
    }

}
