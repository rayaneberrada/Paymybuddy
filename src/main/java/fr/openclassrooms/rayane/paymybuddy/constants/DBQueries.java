package fr.openclassrooms.rayane.paymybuddy.constants;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DBQueries {
  H("SELECT firstName FROM user where id IN()");

  public final String query;
}
