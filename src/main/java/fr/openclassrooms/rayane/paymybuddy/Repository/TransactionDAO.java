package fr.openclassrooms.rayane.paymybuddy.Repository;

import fr.openclassrooms.rayane.paymybuddy.Entity.Card;
import fr.openclassrooms.rayane.paymybuddy.Entity.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

/** TransactionDAO describe the methods expected to be implemented for the DAO */
@Repository
public interface TransactionDAO {
    Boolean sendMoney(int userReceivingId);
    Boolean debitMoney(int amount);
    Boolean addMoney(int amount, Card card);
    List<Transaction> getTransactionsMade();
    Transaction getSpecificTransaction(int transaction_id);
}
