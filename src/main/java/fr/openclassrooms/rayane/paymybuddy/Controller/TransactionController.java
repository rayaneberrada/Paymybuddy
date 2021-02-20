package fr.openclassrooms.rayane.paymybuddy.Controller;

import fr.openclassrooms.rayane.paymybuddy.DTO.TransactionDto;
import fr.openclassrooms.rayane.paymybuddy.Entity.Transaction;
import fr.openclassrooms.rayane.paymybuddy.Repository.TransactionRepository;
import fr.openclassrooms.rayane.paymybuddy.Repository.UserRepository;
import fr.openclassrooms.rayane.paymybuddy.Service.TransactionServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/** Controller class to manage transactions in db */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

  private static Logger logger = LoggerFactory.getLogger(TransactionController.class);

  @Autowired TransactionServiceImpl transactionServiceImpl;

  @Autowired TransactionRepository transactionRepository;

  @Autowired UserRepository userRepository;

  /**
   * Route to send money from one user to another
   *
   * @param transactionDto
   * @param authentication
   */
  @PutMapping("/transfer")
  public Transaction sendMoney(
      @RequestBody TransactionDto transactionDto, Authentication authentication) {
    logger.info("http://localhost:8080/transaction/transfer");
    try {
      return transactionServiceImpl.sendMoney(transactionDto, authentication.getName());
    } catch (Exception e) {
      logger.error("Couldn't proceed transaction: " + e);
      return null;
    }
  }

  /**
   * Route to get informations related to a specific transaction
   *
   * @param id
   * @return Transaction object containing info if success, else null
   */
  @GetMapping("/get/{id}")
  public Transaction getTransactionById(@PathVariable int id) {
    logger.info("http://localhost:8080/transaction/get/" + id);
    try {
      return transactionRepository.findById(id).get();
    } catch (Exception e) {
      logger.error("Couldn't fetch transaction: " + e);
      return null;
    }
  }

  /**
   * Route to get all transactions related to a connected user
   *
   * @return every Transaction belonging to the user making the request, if fail return null
   */
  @GetMapping("/getAll")
  public ArrayList<Transaction> getUserTransactions(Authentication authentication) {
    logger.info("http://localhost:8080/transaction/getAll/");
    try {
      return transactionRepository.findAllByUserSendingId(
          userRepository.findUserByUsername(authentication.getName()).get());
    } catch (Exception e) {
      logger.error("Couldn't fetch transactions: " + e);
      return null;
    }
  }
}
