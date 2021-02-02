package fr.openclassrooms.rayane.paymybuddy.Repository;

import fr.openclassrooms.rayane.paymybuddy.Entity.User;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTestIT {

  private static Logger logger = LoggerFactory.getLogger(UserRepositoryTestIT.class);

  @Autowired UserRepository userRepository;

  @Autowired EntityManager entityManager;

  @Before
  void setUp() {
    Optional<User> userBeforeUpdate = userRepository.findUserByUsername("hervey");
  }

  @Test
  public void Update_EnableUser_ToDisabled() {
    // GIVEN
    Optional<User> user = userRepository.findUserByUsername("rayane");
    boolean userBeforeUpdateEnabled = user.get().enabled;

    // WHEN
    int rowChanged = userRepository.deactivateUser(user.get().id);
    entityManager.refresh(user.get());
    boolean userAfterUpdateEnabled = user.get().enabled;

    // THEN
    assertThat(userBeforeUpdateEnabled).isTrue();
    assertThat(userAfterUpdateEnabled).isFalse();
  }

  @Test
  public void Update_EmailAndUsername_ToNewValue() {
    // GIVEN
    Optional<User> user = userRepository.findUserByUsername("rayane");

    // WHEN
    int rowChanged = userRepository.modifyUser("bill", "bill@gmail.com", 1);
    entityManager.refresh(user.get());

    // THEN
    assertThat(user.get().username).isEqualTo("bill");
    assertThat(user.get().email).isEqualTo("bill@gmail.com");
  }
}
