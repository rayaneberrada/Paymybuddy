package fr.openclassrooms.rayane.paymybuddy.Repository;

import fr.openclassrooms.rayane.paymybuddy.Entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/** BeneficiaryRepository describe the crud methods expected to be implemented for the DAO */
@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer> {
  @Query(
      value =
          "SELECT first_name FROM user where id IN(SELECT user_receiving_id FROM beneficiary WHERE user_sending_id= :user_sending_id)",
      nativeQuery = true)
  List<Beneficiary> getAllUserBeneficiary(@Param("user_sending_id") int id);
}
