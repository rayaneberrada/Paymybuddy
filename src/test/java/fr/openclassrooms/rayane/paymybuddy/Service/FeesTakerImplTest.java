package fr.openclassrooms.rayane.paymybuddy.Service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.*;

public class FeesTakerImplTest {

  private FeesTaker feesTaker = new FeesTakerImpl();
  private static Logger logger = LoggerFactory.getLogger(FeesTakerImplTest.class);

  @Test
  void feesDeducedTest() {
    // GIVEN
    float payment = 50;
    logger.info("Value before deduction: " + payment);
    float excepected = (float) Math.round(payment * 100 * 0.95) / 100;
    logger.info("Value after deduction: " + excepected);

    // WHEN
    float moneyLeft = feesTaker.deduceFees(payment);

    // THEN
    assertThat(excepected).isEqualTo(moneyLeft);
  }
}
