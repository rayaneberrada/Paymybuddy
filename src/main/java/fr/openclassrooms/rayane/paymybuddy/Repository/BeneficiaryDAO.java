package fr.openclassrooms.rayane.paymybuddy.Repository;

import fr.openclassrooms.rayane.paymybuddy.Entity.Beneficiary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeneficiaryDAO {
    Boolean addBeneficiary(String email);
    Boolean removeBeneficiary();
    List<Beneficiary> getAllUserBeneficiary();
}
