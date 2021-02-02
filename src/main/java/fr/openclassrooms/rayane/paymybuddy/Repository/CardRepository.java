package fr.openclassrooms.rayane.paymybuddy.Repository;

import fr.openclassrooms.rayane.paymybuddy.Entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** CardRepository describe the crud methods expected to be implemented for the DAO */
@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
  /**
   * Method to retrieve money from the user to it's bank account
   *
   * @param amount
   */
  @Query(value = "UPDATE User u SET u.money = u.money - :moneyToAdd WHERE u.id = :userId")
  @Modifying
  void debitMoney(@Param("userId") int userId, @Param("moneyToAdd") int amount);

  /**
   * Method to add money on the user app account from it's bank account
   *
   * @param amount
   */
  @Query(value = "UPDATE User u SET u.money = u.money + :moneyToAdd WHERE u.id = :userId")
  @Modifying
  void addMoney(@Param("userId") int userId, @Param("moneyToAdd") int amount);
}
