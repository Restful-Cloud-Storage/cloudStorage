package ie.project.RegistrationAndLogin.uploadFile;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(FileStorageException.class)
    public ModelAndView handleException(FileStorageException exception, RedirectAttributes redirectAttributes) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("message", exception.getMsg());
//        mav.setViewName("error");/**error.html*/
        mav.setViewName("errorIndex");/**error.html*/
//        mav.setViewName("upload_file__e");/**error.html*/
        System.out.println("mav from class AppExceptionHandler :" +mav.toString());
        return mav;

    }
}
