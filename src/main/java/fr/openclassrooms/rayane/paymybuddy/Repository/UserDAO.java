package fr.openclassrooms.rayane.paymybuddy.Repository;

import fr.openclassrooms.rayane.paymybuddy.Entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {
    Boolean addUser(User userToAdd);
    Boolean deactivateUser();
    Boolean modifyUser();
    User getUserInfo();
}
