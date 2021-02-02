package fr.openclassrooms.rayane.paymybuddy.Repository;

import fr.openclassrooms.rayane.paymybuddy.Entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class CardRepositoryTestIT {

  @Autowired EntityManager entityManager;

  @Autowired CardRepository cardRepository;

  @Autowired UserRepository userRepository;

  @Test
  public void Update_UserMoney_AfterAddingMoney() {
    // GIVEN
    Optional<User> user = userRepository.findUserByUsername("rayane");
    int userMoney = user.get().money;

    // WHEN
    cardRepository.addMoney(user.get().id, 100);
    entityManager.refresh(user.get());

    // THEN
    assertThat(user.get().money).isEqualTo(userMoney + 100);
  }

  @Test
  public void Update_UserMoney_AfterDebiting() {
    // GIVEN
    Optional<User> user = userRepository.findUserByUsername("rayane");
    int userMoney = user.get().money;

    // WHEN
    cardRepository.debitMoney(user.get().id, 100);
    entityManager.refresh(user.get());

    // THEN
    assertThat(user.get().money).isEqualTo(userMoney - 100);
  }
}
