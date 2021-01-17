package fr.openclassrooms.rayane.paymybuddy.Entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table( name = "beneficiary" )
public class Beneficiary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull public int id;

    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn(name = "user_sending_id", referencedColumnName = "id")
    @NonNull public User userSendingId;

    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn(name = "user_receiving_id", referencedColumnName = "id")
    @NonNull public User userReceivingId;
}