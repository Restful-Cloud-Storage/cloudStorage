package ie.project.RegistrationAndLogin.Files;

import ie.project.RegistrationAndLogin.uploadFile.FileService;
import org.sonatype.plexus.components.sec.dispatcher.model.Config;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@RestController
    @RequestMapping("/download")
    public class FileDownloadControler {


    private static final int DEFAULT_BUFFER_SIZE = 20480; // ..bytes = 20KB.
    private static final long DEFAULT_EXPIRE_TIME = 604800000L; // ..ms = 1 week.
    private static final String MULTIPART_BOUNDARY = "MULTIPART_BYTERANGES";

//    public static String CurrentFolder;
//
//    public static void setCurrentFolder(String currentFolder) {
//        CurrentFolder = currentFolder;
//        System.out.println("construct :"+ CurrentFolder);
//    }


    public static final String address = "/file";


    @RequestMapping(address + "/{fileName:.+}")
        public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable("fileName") String fileName) throws Exception {
        getUrl GUrl = new getUrl();
         String EXTERNAL_FILE_PATH = GUrl.getUrlPath();

            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String ranges = request.getHeader("Range");
            System.out.println("request url : "+ request.getRequestURL());

            System.out.println("url : " + EXTERNAL_FILE_PATH);

            // if there is no range request in the header than do the "normal" filtering
            if (ranges == null) {


                File file = new File(EXTERNAL_FILE_PATH + fileName);
                if (file.exists()) {
                    //get the mimetype
                    String mimeType = URLConnection.guessContentTypeFromName(file.getName());
                    if (mimeType == null) {
                        //unknown mimetype so set the mimetype to application/octet-stream
                        mimeType = "application/octet-stream";
                    }

                    response.setContentType(mimeType);

                    /**
                     * In a regular HTTP response, the Content-Disposition response header is a
                     * header indicating if the content is expected to be displayed inline in the
                     * browser, that is, as a Web page or as part of a Web page, or as an
                     * attachment, that is downloaded and saved locally.
                     *
                     */

                    /**
                     * Here we have mentioned it to show inline
                     */
//                response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

                    //Here we have mentioned it to show as attachment
                    response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

                    response.setContentLength((int) file.length());

                    InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

                    FileCopyUtils.copy(inputStream, response.getOutputStream());
                }
            }
            else {
                MultipartFileSender.fromPath(Paths.get(EXTERNAL_FILE_PATH + fileName))
                        .with(request)
                        .with(response)
                        .serveResource();
            }
            }
        }


