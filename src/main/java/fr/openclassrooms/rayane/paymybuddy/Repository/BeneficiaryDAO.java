package fr.openclassrooms.rayane.paymybuddy.Repository;

import fr.openclassrooms.rayane.paymybuddy.Entity.Beneficiary;
import org.springframework.stereotype.Repository;

import java.util.List;

/** BeneficiaryDAO describe the crud methods expected to be implemented for the DAO */
@Repository
public interface BeneficiaryDAO {
  Boolean addBeneficiary(String email);

  Boolean removeBeneficiary();

  List<Beneficiary> getAllUserBeneficiary();
}
