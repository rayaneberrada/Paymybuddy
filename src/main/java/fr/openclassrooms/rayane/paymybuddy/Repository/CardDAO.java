package fr.openclassrooms.rayane.paymybuddy.Repository;

import fr.openclassrooms.rayane.paymybuddy.Entity.Card;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CardDAO {
    Boolean addCard(String cardNumber, Date expirationDate);
    List<Card> getCards();
    Boolean removeCard(int id);
}