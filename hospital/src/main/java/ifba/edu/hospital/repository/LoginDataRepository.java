package ifba.edu.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import ifba.edu.hospital.entities.LoginData;

@Repository
public interface LoginDataRepository extends JpaRepository<LoginData, Long>{
    
    UserDetails findByUserName(String username);
}
