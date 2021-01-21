package fr.openclassrooms.rayane.paymybuddy.Repository;

import fr.openclassrooms.rayane.paymybuddy.Entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** CardRepository describe the crud methods expected to be implemented for the DAO */
@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {}
