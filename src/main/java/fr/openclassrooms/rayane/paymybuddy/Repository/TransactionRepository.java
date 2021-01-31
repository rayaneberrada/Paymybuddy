package fr.openclassrooms.rayane.paymybuddy.Repository;

import fr.openclassrooms.rayane.paymybuddy.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
  void sendMoney(
      @Param("userSendingId") int sendingId,
      @Param("userReceivingId") int receivingId,
      @Param("moneySend") int amount);

  // Boolean debitMoney(int amount);

  // Boolean addMoney(int amount, Card card);

  /**
   * The method search for all the cards own by the user in parameter and then all the transactions
   * made by the cards found for this user
   *
   * @param userId
   * @return
   */
  @Query(
      value =
          "SELECT * FROM transactions WHERE card_id IN (SELECT id FROM card WHERE user_id = :userId)",
      nativeQuery = true)
  List<Transaction> getTransactionsMade(@Param("userId") int userId);
}
