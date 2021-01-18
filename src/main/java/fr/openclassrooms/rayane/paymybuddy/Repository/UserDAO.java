package fr.openclassrooms.rayane.paymybuddy.Repository;

import fr.openclassrooms.rayane.paymybuddy.Entity.User;
import org.springframework.stereotype.Repository;

/** BeneficiaryDAO describe the crud methods expected to be implemented for the DAO */
@Repository
public interface UserDAO {
  Boolean addUser(User userToAdd);

  Boolean deactivateUser();

  Boolean modifyUser();

  User getUserInfo();
}
