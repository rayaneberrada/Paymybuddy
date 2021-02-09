package fr.openclassrooms.rayane.paymybuddy.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CardDto {
  String number;
  Date expirationDate;
}
