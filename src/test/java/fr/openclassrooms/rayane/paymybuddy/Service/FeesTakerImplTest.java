package fr.openclassrooms.rayane.paymybuddy.Service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.*;

public class FeesTakerImplTest {

  private FeesTaker feesTaker = new FeesTakerImpl();
  private static Logger logger = LoggerFactory.getLogger(FeesTakerImplTest.class);

  @Test
  void feesDeducedTest() {
    // GIVEN
    BigDecimal payment = new BigDecimal(50);
    logger.info("Value before deduction: " + payment);
    float excepected =
        payment
            .multiply(new BigDecimal(100))
            .multiply(new BigDecimal(0.95))
            .divide(new BigDecimal(100))
            .setScale(2, RoundingMode.HALF_UP)
            .floatValue();
    logger.info("Value after deduction: " + excepected);

    // WHEN
    float moneyLeft = feesTaker.deduceFees(payment);

    // THEN
    assertThat(excepected).isEqualTo(moneyLeft);
  }
}
