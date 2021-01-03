package ie.project.RegistrationAndLogin.Folders;//package cloudStorage.Folders;
//
//
//import cloudStorage.Folders.Folder;
//import com.springboot.pagination.model.Movie;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface folderRepository extends JpaRepository<Folder, Long> {
//
//    // Fetch all movies by their release status and order them by movie them in ascending order.
//    Page<Folder> findByReleasedOrderByTitleAsc(boolean released, Pageable pageable);
//}