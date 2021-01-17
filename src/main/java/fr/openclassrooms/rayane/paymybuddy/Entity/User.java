package fr.openclassrooms.rayane.paymybuddy.Entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table( name = "user" )
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull public int Id;

    @NonNull public String firstName;
    @NonNull public String lastName;
    @NonNull public String email;
    @NonNull public int money;
    @NonNull public Boolean activated;

    @OneToOne(mappedBy = "beneficiary")
    public Beneficiary beneficiary;

    @OneToOne( mappedBy = "card")
    public Card card;

    @OneToOne(mappedBy = "transaction")
    public Transaction transaction;
}
