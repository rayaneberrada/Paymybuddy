package fr.openclassrooms.rayane.paymybuddy.Service;

import fr.openclassrooms.rayane.paymybuddy.Entity.User;
import fr.openclassrooms.rayane.paymybuddy.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
