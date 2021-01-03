package ie.project.RegistrationAndLogin.Files;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class getFileName {

    public List<String> getFilesName(String str) {
        // Creates an array in which we will store the names of files and directories
        List<String> pathnames;

        // Creates a new File instance by converting the given pathname string
        // into an abstract pathname
        File f = new File(str);

        // Populates the array with names of files and directories
        Files.filesList = Arrays.asList(f.list());

        // For each pathname in the pathnames array
        for (String pathname : Files.filesList) {
            // Print the names of files and directories
            System.out.println(pathname);
        }
        return Files.filesList;
    }
}
