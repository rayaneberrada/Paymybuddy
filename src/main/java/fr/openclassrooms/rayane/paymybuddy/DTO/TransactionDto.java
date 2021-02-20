package fr.openclassrooms.rayane.paymybuddy.DTO;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class TransactionDto {
  @NonNull int receivingId;
  @NonNull float amount;
}
