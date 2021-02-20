package fr.openclassrooms.rayane.paymybuddy.Controller;

import fr.openclassrooms.rayane.paymybuddy.DTO.CardDto;
import fr.openclassrooms.rayane.paymybuddy.DTO.TransactionDto;
import fr.openclassrooms.rayane.paymybuddy.Entity.Card;
import fr.openclassrooms.rayane.paymybuddy.Entity.Transaction;
import fr.openclassrooms.rayane.paymybuddy.Repository.CardRepository;
import fr.openclassrooms.rayane.paymybuddy.Repository.UserRepository;
import fr.openclassrooms.rayane.paymybuddy.Service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/** Class to manage cards in db */
@RestController
@RequestMapping("/card")
public class CardController {
  private static Logger logger = LoggerFactory.getLogger(CardController.class);

  @Autowired CardRepository cardRepository;

  @Autowired UserRepository userRepository;

  @Autowired TransactionService transactionService;

  /**
   * Route to add a card in database
   *
   * @param cardToAdd
   * @param authentication
   * @return card if added, else null
   */
  @PostMapping("/add")
  public Card addCard(@RequestBody CardDto cardToAdd, Authentication authentication) {
    logger.info("http://localhost:8080/card/add");
    try {
      return cardRepository.save(
          Card.builder()
              .expirationDate(cardToAdd.getExpirationDate())
              .number(cardToAdd.getNumber())
              .userId(userRepository.findUserByUsername(authentication.getName()).get())
              .build());
    } catch (Exception e) {
      logger.error(
          "Impossible to add car to user " + authentication.getName() + " Exception: " + e);
      return null;
    }
  }

  /**
   * Route to add money to user account
   *
   * @param amount
   * @param authentication
   * @return 1 if value succesfully update, else 0
   */
  @PutMapping("/addMoney")
  public Transaction addMoney(TransactionDto transactionDto, Authentication authentication) {
    logger.info("http://localhost:8080/card/add");
    try {
      return transactionService.addMoney(transactionDto, authentication.getName());
    } catch (Exception e) {
      logger.error("impossible to add money: " + e);
      return null;
    }
  }

  /**
   * Route to remove money from user account to user bank account
   *
   * @param amount
   * @param authentication
   * @return 1 if successfully updated, else 0
   */
  @PutMapping("/debitMoney")
  public Transaction debitMoney(TransactionDto transactionDto, Authentication authentication) {
    logger.info("http://localhost:8080/card/add");
    try {
      return transactionService.debitMoney(transactionDto, authentication.getName());
    } catch (Exception e) {
      logger.error("impossible to add money: " + e);
      return null;
    }
  }

  /**
   * Route to remove card from user cards
   *
   * @param cardDto
   * @param authentication
   */
  @DeleteMapping("/delete")
  public int deleteCard(@RequestBody CardDto cardDto, Authentication authentication) {
    logger.info("http://localhost:8080/card/delete");
    try {
      return cardRepository.delete(
          userRepository.findUserByUsername(authentication.getName()).get(), cardDto.getNumber());
    } catch (Exception e) {
      logger.error("card coudldn't be deleted: " + e);
      return 0;
    }
  }

  /**
   * Route to get card informations from user
   *
   * @param cardDto
   * @return Card if found, else null
   */
  @GetMapping("/get")
  public Card getCard(@RequestBody CardDto cardDto) {
    logger.info("http://localhost:8080/card/get");
    try {
      return cardRepository.findCardByNumber(cardDto.getNumber());
    } catch (Exception e) {
      logger.error("card coudldn't be found: " + e);
      return null;
    }
  }

  /**
   * Route to get all cards related to a specific user
   *
   * @param authentication
   * @return all Card that belong to a user, else null
   */
  @GetMapping("/getAll")
  public ArrayList<Card> getCard(Authentication authentication) {
    logger.info("http://localhost:8080/card/getAll");
    try {
      return cardRepository.findAllByUserId(
          userRepository.findUserByUsername(authentication.getName()).get());
    } catch (Exception e) {
      logger.error("card coudldn't be found: " + e);
      return null;
    }
  }
}
