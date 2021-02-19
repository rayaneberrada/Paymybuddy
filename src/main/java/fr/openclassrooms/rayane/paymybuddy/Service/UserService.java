package fr.openclassrooms.rayane.paymybuddy.Service;

import fr.openclassrooms.rayane.paymybuddy.Entity.User;
import fr.openclassrooms.rayane.paymybuddy.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This class is a custom implementation of UserDetailsService and has for purpose to verify user
 * credentials using informations contained in database
 */
@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findUserByUsername(userName);

    user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

    return user.get();
  }
}
