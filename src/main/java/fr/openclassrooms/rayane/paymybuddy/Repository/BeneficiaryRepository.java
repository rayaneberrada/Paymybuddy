package fr.openclassrooms.rayane.paymybuddy.Repository;

import fr.openclassrooms.rayane.paymybuddy.Entity.Beneficiary;
import fr.openclassrooms.rayane.paymybuddy.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/** BeneficiaryRepository describe the crud methods expected to be implemented for the DAO */
@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Integer> {

  @Modifying
  @Transactional
  @Query(
      value =
          "INSERT INTO beneficiary(user_sending_id, user_receiving_id) VALUES (:userSendingId, :userReceivingId)",
      nativeQuery = true)
  int save(
      @Param("userSendingId") int userSendingId, @Param("userReceivingId") int userReceivingId);

  @Modifying
  @Transactional
  @Query(
      value =
          "DELETE FROM beneficiary WHERE user_receiving_id=:userReceivingId AND user_sending_id=:userSendingId",
      nativeQuery = true)
  int delete(
      @Param("userSendingId") int userSendingId, @Param("userReceivingId") int userReceivingId);

  ArrayList<Beneficiary> findAllByUserSendingId(User user);
}
