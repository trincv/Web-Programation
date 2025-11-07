package ifba.edu.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifba.edu.hospital.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    
}
