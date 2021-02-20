package fr.openclassrooms.rayane.paymybuddy.Repository;

import fr.openclassrooms.rayane.paymybuddy.Entity.Transaction;
import fr.openclassrooms.rayane.paymybuddy.Entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class TransactionRepositoryTestIT {

  @Autowired TransactionRepository transactionRepository;

  @Autowired UserRepository userRepository;

  @Autowired EntityManager entityManager;

  @Test
  public void Update_UsersMoney_AfterSendingToBeneficiary() {
    // GIVEN
    Optional<User> userSending = userRepository.findUserByUsername("rayane");
    Optional<User> userReceiving = userRepository.findUserByUsername("john");
    float userSendingMoney = userSending.get().money;
    float userReceivingMoney = userReceiving.get().money;

    // WHEN
    transactionRepository.sendMoney(userSending.get().id, userReceiving.get().id, 50);
    entityManager.refresh(userSending.get());
    entityManager.refresh(userReceiving.get());

    // THEN
    assertThat(userSending.get().money).isEqualTo(userSendingMoney - 50);
    assertThat(userReceiving.get().money).isEqualTo(userReceivingMoney + 50);
  }

  public void SelectAll_Transactions_FromUser() {
    // GIVEN
    Optional<User> user = userRepository.findUserByUsername("daniel");

    // WHEN
    // Optional<List<Transaction>> tranasctions =
    // transactionRepository.findAllByUserSendingId(user.get());

    // THEN
    // assertThat(tranasctions.get().size()).isEqualTo(2);
  }

  @Test
  public void SelectById_Transaction_FromTransactions() {
    // GIVEN
    int transactionToFetchId = 1;

    // WHEN
    Optional<Transaction> transactionFetched =
        transactionRepository.findTransactionsById(transactionToFetchId);

    // THEN
    assertThat(transactionFetched.get().userSendingId.id).isEqualTo(1);
    assertThat(transactionFetched.get().amount).isEqualTo(300);
  }
}
