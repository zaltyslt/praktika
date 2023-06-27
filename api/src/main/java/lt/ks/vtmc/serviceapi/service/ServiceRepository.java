package lt.ks.vtmc.serviceapi.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<AutoService, Long> {


    Optional<AutoService> findByTitle(String title);
//    Optional<User> findByUsernameAndRole(String username, String role);
//
//    boolean existsByUsername(String username);
//
//    boolean existsByEmail(String email);
}
