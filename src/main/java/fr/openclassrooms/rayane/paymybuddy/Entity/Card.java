package fr.openclassrooms.rayane.paymybuddy.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card")
public class Card {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int id;

  @Column(length = 50)
  @Size(min = 16, max = 16)
  public String number;

  @NonNull public Date expirationDate;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @NonNull
  @JsonIgnore
  public User userId;
}
