package ie.project.RegistrationAndLogin.uploadFile;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
/**
 *  It tells Spring that this is a service class. Typically all the business logic is written in this layer.
 */
public class FileService {
    public String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

//    @Value(value = "${app.upload.dir:${user.home}}")/** age dir tarif nashode bashe tuye user.home save mishe (envirenment e har os hastesh)
    /**
     *  which we will be using to store the path of the directory we want our file to be uploaded to
     */

    public void uploadFile(MultipartFile file) {

        System.out.println("inner"+ uploadDir);
        try {
            /**
             * we taeke the full path of the file and upload file the file separator is \ while in Linux it's /.
             */
            Path copyLocation = Paths
                .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            System.out.println("copy location : "+copyLocation);
            /**
             * Then we copy the file to the location using Files.copy. The REPLACE_EXISTING copy option will override any file with the same name there.
             */
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            /**
             * If there is an Exception in this whole process we captured it and threw a custom FileStorageException exception.
             */
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename()
                + ". Please try again!");
        }
    }
}
