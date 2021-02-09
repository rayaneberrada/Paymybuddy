package fr.openclassrooms.rayane.paymybuddy.Controller;

import fr.openclassrooms.rayane.paymybuddy.DTO.CardDto;
import fr.openclassrooms.rayane.paymybuddy.Entity.Card;
import fr.openclassrooms.rayane.paymybuddy.Repository.CardRepository;
import fr.openclassrooms.rayane.paymybuddy.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/card")
public class CardController {
  private static Logger logger = LoggerFactory.getLogger(CardController.class);

  @Autowired CardRepository cardRepository;

  @Autowired UserRepository userRepository;

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

  @PutMapping("/addMoney")
  public int addMoney(int amount, Authentication authentication) {
    logger.info("http://localhost:8080/card/add");
    try {
      return cardRepository.addMoney(
          userRepository.findUserByUsername(authentication.getName()).get().getId(), amount);
    } catch (Exception e) {
      logger.error("impossible to add money: " + e);
      return 0;
    }
  }

  @PutMapping("/debitMoney")
  public int debitMoney(int amount, Authentication authentication) {
    logger.info("http://localhost:8080/card/add");
    try {
      return cardRepository.debitMoney(
          userRepository.findUserByUsername(authentication.getName()).get().getId(), amount);
    } catch (Exception e) {
      logger.error("impossible to debit money: " + e);
      return 0;
    }
  }

  @DeleteMapping("/delete")
  public void deleteCard(@RequestBody CardDto cardDto, Authentication authentication) {
    logger.info("http://localhost:8080/card/delete");
    try {
      cardRepository.delete(
          userRepository.findUserByUsername(authentication.getName()).get(), cardDto.getNumber());
    } catch (Exception e) {
      logger.error("card coudldn't be deleted: " + e);
    }
  }

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
