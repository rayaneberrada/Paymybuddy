package fr.openclassrooms.rayane.paymybuddy.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** This class contain the method to apply fees on transactions */
public class FeesTakerImpl implements FeesTaker {

  @Override
  public BigDecimal deduceFees(BigDecimal amount) {

    BigDecimal newAmount =
        amount.multiply(BigDecimal.valueOf(0.95)).setScale(2, RoundingMode.CEILING);
    return newAmount;
  }
}
