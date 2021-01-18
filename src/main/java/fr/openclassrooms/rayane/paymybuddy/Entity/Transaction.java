package fr.openclassrooms.rayane.paymybuddy.Entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "transaction")
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @NonNull
  public int id;

  @NonNull public int amount;
  @NonNull public Date date;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_receiving_id", referencedColumnName = "id")
  @NonNull
  public User userReceivingId;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "card_id", referencedColumnName = "id")
  @NonNull
  public Card cardId;
}
