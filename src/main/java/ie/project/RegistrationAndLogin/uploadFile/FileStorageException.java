package ie.project.RegistrationAndLogin.uploadFile;

public class FileStorageException extends RuntimeException {

    /**
     *  FileStorageException for any exception during the file upload process. It's a simple class that extends RuntimeException:
     */
    private static final long serialVersionUID = 1L;
    private String msg;

    public FileStorageException(String msg) {
        this.msg = msg;

    }

    public String getMsg() {
        return msg;
    }
}
