package fr.openclassrooms.rayane.paymybuddy.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "beneficiary")
public class Beneficiary {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int id;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_sending_id", referencedColumnName = "id")
  @NonNull
  public User userSendingId;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_receiving_id", referencedColumnName = "id")
  @NonNull
  public User userReceivingId;
}
