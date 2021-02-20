package fr.openclassrooms.rayane.paymybuddy.Service;

import fr.openclassrooms.rayane.paymybuddy.DTO.TransactionDto;
import fr.openclassrooms.rayane.paymybuddy.Entity.User;
import fr.openclassrooms.rayane.paymybuddy.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TransactionServiceTestIT {

  @Autowired EntityManager entityManager;

  @Autowired TransactionService transactionService;

  @Autowired UserRepository userRepository;

  @Test
  @Transactional
  public void addMoneyTest() {
    // GIVEN
    User user = userRepository.findById(1).get();
    float userMoneyBeforeAdding = user.getMoney();

    TransactionDto transaction = new TransactionDto();
    transaction.setAmount((float) 33.45);
    transaction.setReceivingId(user.getId());

    // WHEN
    transactionService.addMoney(transaction, user.getUsername());
    entityManager.refresh(user);

    // THEN
    assertThat(userMoneyBeforeAdding + transaction.getAmount()).isEqualTo(user.getMoney());
  }

  @Test
  @Transactional
  public void debitMoneyTest() {
    // GIVEN
    User user = userRepository.findById(1).get();
    float userMoneyBeforeDebiting = user.getMoney();

    TransactionDto transaction = new TransactionDto();
    transaction.setAmount((float) 33.45);
    transaction.setReceivingId(user.getId());

    // WHEN
    transactionService.debitMoney(transaction, user.getUsername());
    entityManager.refresh(user);

    // THEN
    assertThat(userMoneyBeforeDebiting - transaction.getAmount()).isEqualTo(user.getMoney());
  }

  @Test
  @Transactional
  public void sendMoneyTest() {
    // GIVEN
    User sender = userRepository.findById(1).get();
    float senderMoneyBeforeAdding = sender.getMoney();
    User receiver = userRepository.findById(2).get();
    float receiverMoneyBeforeAdding = receiver.getMoney();

    TransactionDto transaction = new TransactionDto();
    transaction.setAmount((float) 58.47);
    transaction.setReceivingId(receiver.getId());

    // WHEN
    transactionService.sendMoney(transaction, sender.getUsername());
    entityManager.refresh(sender);
    entityManager.refresh(receiver);

    // THEN
    assertThat(senderMoneyBeforeAdding - transaction.getAmount()).isEqualTo(sender.getMoney());
    assertThat(receiverMoneyBeforeAdding + transaction.getAmount()).isEqualTo(receiver.getMoney());
  }
}
