package fr.openclassrooms.rayane.paymybuddy.Repository;

import fr.openclassrooms.rayane.paymybuddy.Entity.Beneficiary;
import fr.openclassrooms.rayane.paymybuddy.Entity.User;

import java.util.List;

public interface BeneficiaryRepositoryCustom {
  List<Beneficiary> getAllUserBeneficiary(User user);
}
