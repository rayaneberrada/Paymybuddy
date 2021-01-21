package fr.openclassrooms.rayane.paymybuddy.Repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** BeneficiaryRepository describe the crud methods expected to be implemented for the DAO */
@Repository
public interface UserRepository {

  @Query(value = "UPDATE User u SET u.activated = false WHERE u.id = :userToModifyId")
  @Modifying
  Boolean deactivateUser(@Param("userToModifyId") int userToModify);

  @Query(
      value =
          "UPDATE User u SET u.firstName = :firstName, "
              + "u.lastName = :lastName, "
              + "u.email = :email "
              + "WHERE u.id = :userToModifyId")
  Boolean modifyUser(
      @Param("firstName") String firstName,
      @Param("lastName") String lastName,
      @Param("email") String email);
}
