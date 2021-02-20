package fr.openclassrooms.rayane.paymybuddy.Service;

import fr.openclassrooms.rayane.paymybuddy.DTO.TransactionDto;
import fr.openclassrooms.rayane.paymybuddy.Entity.Transaction;
import fr.openclassrooms.rayane.paymybuddy.Repository.CardRepository;
import fr.openclassrooms.rayane.paymybuddy.Repository.TransactionRepository;
import fr.openclassrooms.rayane.paymybuddy.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/** Service to manage and register transactions */
@Service
public class TransactionService {

  private static Logger logger = LoggerFactory.getLogger(TransactionService.class);

  @Autowired TransactionRepository transactionRepository;

  @Autowired CardRepository cardRepository;

  @Autowired UserRepository userRepository;

  @Autowired FeesTakerImpl feesTaker;

  /**
   * This method update user's money amount and save a transaction related to it in db
   *
   * @param transactionDto
   * @param username
   * @return Transaction object of the exchange between user's
   */
  @Transactional(rollbackFor = Exception.class)
  public Transaction sendMoney(TransactionDto transactionDto, String username) {
    transactionDto.setAmount(feesTaker.deduceFees(transactionDto.getAmount()));

    transactionRepository.sendMoney(
        userRepository.findUserByUsername(username).get().getId(),
        transactionDto.getReceivingId(),
        transactionDto.getAmount());

    return transactionRepository.save(
        Transaction.builder()
            .amount(transactionDto.getAmount())
            .userSendingId(userRepository.findUserByUsername(username).get())
            .userReceivingId(userRepository.findById(transactionDto.getReceivingId()).get())
            .date(new Date(System.currentTimeMillis()))
            .build());
  }

  /**
   * Method to add money to the user from it's card and save a transaction related to it in db
   *
   * @param transactionDto
   * @param username
   * @return Transaction object related to the action
   */
  @Transactional(rollbackFor = Exception.class)
  public Transaction addMoney(TransactionDto transactionDto, String username) {
    int userId = userRepository.findUserByUsername(username).get().getId();

    cardRepository.addMoney(userId, transactionDto.getAmount());

    return transactionRepository.save(
        Transaction.builder()
            .userReceivingId(userRepository.findById(userId).get())
            .userSendingId(userRepository.findById(userId).get())
            .amount(transactionDto.getAmount())
            .date(new Date(System.currentTimeMillis()))
            .build());
  }

  /**
   * Method to dbit money to the user from it's user account and save a transaction related to it in
   * db
   *
   * @param transactionDto
   * @param username
   * @return Transaction object related to the action
   */
  @Transactional(rollbackFor = Exception.class)
  public Transaction debitMoney(TransactionDto transactionDto, String username) {
    int userId = userRepository.findUserByUsername(username).get().getId();

    cardRepository.debitMoney(userId, transactionDto.getAmount());

    return transactionRepository.save(
        Transaction.builder()
            .userReceivingId(userRepository.findById(userId).get())
            .userSendingId(userRepository.findById(userId).get())
            .amount(transactionDto.getAmount())
            .date(new Date(System.currentTimeMillis()))
            .build());
  }
}
