package fr.openclassrooms.rayane.paymybuddy.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Table(name = "transactions")
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NonNull
  public int id;

  @NonNull public float amount;
  @NonNull public Date date;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_receiving_id", referencedColumnName = "id")
  @NonNull
  @JsonIgnore
  public User userReceivingId;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_sending_id", referencedColumnName = "id")
  @NonNull
  @JsonIgnore
  public User userSendingId;
}
