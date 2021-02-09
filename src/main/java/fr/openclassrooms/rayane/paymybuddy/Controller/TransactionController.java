package fr.openclassrooms.rayane.paymybuddy.Controller;

import fr.openclassrooms.rayane.paymybuddy.DTO.TransactionDto;
import fr.openclassrooms.rayane.paymybuddy.Entity.Transaction;
import fr.openclassrooms.rayane.paymybuddy.Repository.TransactionRepository;
import fr.openclassrooms.rayane.paymybuddy.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

  private static Logger logger = LoggerFactory.getLogger(TransactionController.class);

  @Autowired TransactionRepository transactionRepository;

  @Autowired UserRepository userRepository;

  @PutMapping("/transfer")
  public void sendMoney(@RequestBody TransactionDto transactionDto, Authentication authentication) {
    logger.info("http://localhost:8080/transaction/transfer");
    try {
      transactionRepository.sendMoney(
          userRepository.findUserByUsername(authentication.getName()).get().getId(),
          transactionDto.getReceivingId(),
          transactionDto.getAmount());

      transactionRepository.save(
          Transaction.builder()
              .amount(transactionDto.getAmount())
              .userSendingId(userRepository.findUserByUsername(authentication.getName()).get())
              .userReceivingId(userRepository.findById(transactionDto.getReceivingId()).get())
              .date(new Date(System.currentTimeMillis()))
              .build());
    } catch (Exception e) {
      logger.error("couldn't send money: " + e);
    }
  }

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

  @GetMapping("/getAll")
  public ArrayList<Transaction> getUserTransactions(int userId) {
    logger.info("http://localhost:8080/transaction/getAll/" + userId);
    try {
      return transactionRepository.findAllByUserSendingId(userRepository.findById(userId).get());
    } catch (Exception e) {
      logger.error("Couldn't fetch transactions: " + e);
      return null;
    }
  }
}
