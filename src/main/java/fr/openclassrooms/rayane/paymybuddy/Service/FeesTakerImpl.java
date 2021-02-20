package fr.openclassrooms.rayane.paymybuddy.Service;

import org.springframework.stereotype.Service;

/** This class contain the method to apply fees on transactions */
@Service
public class FeesTakerImpl implements FeesTaker {

  @Override
  public float deduceFees(float amount) {
    return (float) (Math.round(amount * 100 * 0.95)) / 100;
  }
}
