package fr.openclassrooms.rayane.paymybuddy.Service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

public class FeesTakerImplTest {

  private FeesTaker feesTaker = new FeesTakerImpl();
  private static Logger logger = LoggerFactory.getLogger(FeesTakerImplTest.class);

  @Test
  void feesDeducedTest() {
    // GIVEN
    BigDecimal payment = new BigDecimal(50);
    logger.info("Value before deduction: " + payment);
    BigDecimal excepected = payment.multiply(BigDecimal.valueOf(0.95));
    logger.info("Value after deduction: " + excepected);

    // WHEN
    BigDecimal moneyLeft = feesTaker.deduceFees(payment);

    // THEN
    assertThat(excepected).isEqualTo(moneyLeft);
  }
}
