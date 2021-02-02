package fr.openclassrooms.rayane.paymybuddy.Entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "card")
public class Card {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NonNull
  public int id;

  @Column(length = 50)
  @Size(min = 16, max = 16)
  public String number;

  @NonNull public Date expirationDate;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @NonNull
  public User userId;
}
