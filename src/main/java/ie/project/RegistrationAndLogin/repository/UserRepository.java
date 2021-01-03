package ie.project.RegistrationAndLogin.repository;

import ie.project.RegistrationAndLogin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByResetPasswordToken(String token);
}
