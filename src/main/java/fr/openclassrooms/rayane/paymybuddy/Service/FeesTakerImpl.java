package fr.openclassrooms.rayane.paymybuddy.Service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** This class contain the method to apply fees on transactions */
@Service
public class FeesTakerImpl implements FeesTaker {

  @Override
  public float deduceFees(BigDecimal amount) {
    return amount
        .multiply(new BigDecimal(100))
        .multiply(new BigDecimal(0.95))
        .divide(new BigDecimal(100))
        .setScale(2, RoundingMode.HALF_UP)
        .floatValue();
  }
}
