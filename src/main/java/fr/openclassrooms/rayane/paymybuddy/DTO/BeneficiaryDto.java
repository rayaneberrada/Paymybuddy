package fr.openclassrooms.rayane.paymybuddy.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BeneficiaryDto {
  int userSendingId;
  int userReceivingId;
}
