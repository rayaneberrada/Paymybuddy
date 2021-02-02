package fr.openclassrooms.rayane.paymybuddy.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BeneficiaryDto {
  int userReceivingId;
  int userSendingId;
}
