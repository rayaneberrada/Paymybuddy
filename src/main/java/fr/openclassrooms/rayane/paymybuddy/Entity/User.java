package fr.openclassrooms.rayane.paymybuddy.Entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NonNull
  public int id;

  @NonNull public String firstName;
  @NonNull public String lastName;
  @NonNull public String email;
  @NonNull public int money;
  @NonNull public Boolean activated;

  @OneToOne(mappedBy = "userSendingId")
  public Beneficiary beneficiarySending;

  @OneToOne(mappedBy = "userReceivingId")
  public Beneficiary beneficiaryReceiving;

  @OneToOne(mappedBy = "userId")
  public Card card;

  @OneToOne(mappedBy = "userReceivingId")
  public Transaction transaction;
}
