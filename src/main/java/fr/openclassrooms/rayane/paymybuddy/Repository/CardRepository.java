package fr.openclassrooms.rayane.paymybuddy.Repository;

import fr.openclassrooms.rayane.paymybuddy.Entity.Card;
import fr.openclassrooms.rayane.paymybuddy.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

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
  @Transactional
  int debitMoney(@Param("userId") int userId, @Param("moneyToAdd") int amount);

  /**
   * Method to add money on the user app account from it's bank account
   *
   * @param amount
   */
  @Query(value = "UPDATE User u SET u.money = u.money + :moneyToAdd WHERE u.id = :userId")
  @Modifying
  @Transactional
  int addMoney(@Param("userId") int userId, @Param("moneyToAdd") int amount);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM card WHERE user_id=:user AND number = :number", nativeQuery = true)
  int delete(@Param("user") User userId, @Param("number") String carNumber);

  ArrayList<Card> findAllByUserId(User user);

  Card findCardByNumber(String number);

  Card save(Card card);
}
