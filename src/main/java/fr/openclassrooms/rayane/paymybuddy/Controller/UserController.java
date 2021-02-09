package fr.openclassrooms.rayane.paymybuddy.Controller;

import fr.openclassrooms.rayane.paymybuddy.DTO.UserDto;
import fr.openclassrooms.rayane.paymybuddy.Entity.User;
import fr.openclassrooms.rayane.paymybuddy.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/** Class to manage users through routes */
@RestController
@RequestMapping("/user")
public class UserController {

  private static Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired UserRepository userRepository;

  @PutMapping("/deactivate/{id}")
  public void deactivateUser(@PathVariable int id) {
    logger.info("http://localhost:8080/user/deactivate/" + id);
    try {
      userRepository.deactivateUser(id);
    } catch (Exception e) {
      logger.error("Couldn't deactivate user. Exception raised: " + e);
    }
  }

  /**
   * A user can be modified using UserDto data to replace ones in database
   *
   * @param userDto
   */
  @PutMapping("/modify")
  public void modifyUser(@RequestBody UserDto userDto) {
    logger.info("http://localhost:8080/user/get");
    try {
      userRepository.modifyUser(userDto.username, userDto.email, userDto.id);
    } catch (Exception e) {
      logger.error("Couldn't modify user: " + e);
    }
  }

  @PostMapping("/add")
  public void addUser(@RequestBody User user) {
    logger.info("http://localhost:8080/user/add");
    try {
      userRepository.save(user);
    } catch (Exception e) {
      logger.error("Couldn't save user: " + e);
    }
  }

  @GetMapping("/get/{id}")
  public User getUser(@PathVariable int id) {
    logger.info("http://localhost:8080/user/get/" + id);
    try {
      return userRepository.findById(id).get();
    } catch (Exception e) {
      logger.error("Couldn't fetch user: " + e);
      return null;
    }
  }
}
