package ie.project.RegistrationAndLogin.service;


import ie.project.RegistrationAndLogin.model.User;
import ie.project.RegistrationAndLogin.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto userRegistrationDto);
    void updateResetPasswordToken(String token, String email);
    User getByResetPasswordToken(String token);
    void updatePassword(User user, String newPassword);
}
