package fr.openclassrooms.rayane.paymybuddy.Repository;

import fr.openclassrooms.rayane.paymybuddy.Entity.Transaction;
import fr.openclassrooms.rayane.paymybuddy.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/** TransactionRepository describe the methods expected to be implemented for the DAO */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

  /**
   * Method to send money from one user to another
   *
   * @param sendingId id of the user sending the money
   * @param receivingId id of the user receiving the money
   * @param amount amount of money send
   */
  @Query(
      value =
          "UPDATE user\n"
              + "   SET money = CASE id\n"
              + "                      WHEN :userSendingId THEN money - :moneySend\n"
              + "                      WHEN :userReceivingId THEN money + :moneySend\n"
              + "                      END\n"
              + " WHERE id IN(:userSendingId, :userReceivingId);",
      nativeQuery = true)
  @Modifying
  void sendMoney(
      @Param("userSendingId") int sendingId,
      @Param("userReceivingId") int receivingId,
      @Param("moneySend") int amount);

  Optional<List<Transaction>> findAllByUserSendingId(User userSending);

  Optional<Transaction> findTransactionsById(int id);
}
