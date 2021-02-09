package fr.openclassrooms.rayane.paymybuddy.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NonNull
  public int id;

  @NonNull public String username;
  @NonNull public String email;
  @NonNull public String password;
  @NonNull public int money;
  @NonNull public Boolean enabled;
  @NonNull public String role;

  @OneToMany(mappedBy = "userSendingId")
  @JsonIgnore
  public Set<Beneficiary> beneficiarySending;

  @OneToMany(mappedBy = "userReceivingId")
  @JsonIgnore
  public Set<Beneficiary> beneficiaryReceiving;

  @OneToMany(mappedBy = "userId")
  @JsonIgnore
  public Set<Card> card;

  @OneToMany(mappedBy = "userReceivingId")
  @JsonIgnore
  public Set<Transaction> transaction;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Arrays.asList(new SimpleGrantedAuthority(this.role));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.enabled;
  }
}
