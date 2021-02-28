package fr.openclassrooms.rayane.paymybuddy.Service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * FeesTaker is a class supposed to handle the application fees on the transactions made by the
 * users
 */
@Service
public interface FeesTaker {
  float deduceFees(BigDecimal amount);
}
