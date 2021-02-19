package fr.openclassrooms.rayane.paymybuddy.DTO;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class UserDtoCustom {
  @NonNull public int id;
  @NonNull public String username;
  @NonNull public String email;
  @NonNull public String password;
}
