package fr.openclassrooms.rayane.paymybuddy.Repository;

import fr.openclassrooms.rayane.paymybuddy.Entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** BeneficiaryRepository describe the crud methods expected to be implemented for the DAO */
@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer> {}
