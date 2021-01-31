package fr.openclassrooms.rayane.paymybuddy.Repository;

import fr.openclassrooms.rayane.paymybuddy.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/** BeneficiaryRepository describe the crud methods expected to be implemented for the DAO */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  /**
   * @param userToModify
   * @return true or false depending if the user has been successfully deactivated
   */
  @Query(value = "UPDATE User u SET u.enabled = false WHERE u.id = :userToModifyId")
  @Modifying
  Boolean deactivateUser(@Param("userToModifyId") int userToModify);

  /**
   * Method allowing to modify the username and email of an user
   *
   * @param userName
   * @param email
   * @return true or false depending if the user has been modified successfully
   */
  @Query(
      value =
          "UPDATE User u SET u.username = :userName, "
              + "u.email = :email "
              + "WHERE u.id = :userToModifyId")
  Boolean modifyUser(@Param("userName") String userName, @Param("email") String email);

  Optional<User> findUserByUsername(String username);
}
