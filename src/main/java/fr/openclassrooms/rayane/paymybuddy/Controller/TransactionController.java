package fr.openclassrooms.rayane.paymybuddy.Controller;

import fr.openclassrooms.rayane.paymybuddy.Repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

  private static Logger logger = LoggerFactory.getLogger(TransactionController.class);

  @Autowired TransactionRepository transactionRepository;

  // int sendingId, int receivingId, int amount
  @GetMapping("/transfer")
  public Boolean sendMoney() {
    logger.info("http://localhost:8080/transaction/transfer");
    return true;
  }
}
