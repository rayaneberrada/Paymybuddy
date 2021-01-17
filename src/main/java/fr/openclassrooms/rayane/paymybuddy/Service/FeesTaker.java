package fr.openclassrooms.rayane.paymybuddy.Service;

import org.springframework.stereotype.Service;

@Service
public interface FeesTaker {
    Boolean deduceFees();
}
