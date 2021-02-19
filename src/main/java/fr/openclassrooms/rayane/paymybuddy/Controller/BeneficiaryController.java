package fr.openclassrooms.rayane.paymybuddy.Controller;

import fr.openclassrooms.rayane.paymybuddy.DTO.BeneficiaryDto;
import fr.openclassrooms.rayane.paymybuddy.Entity.Beneficiary;
import fr.openclassrooms.rayane.paymybuddy.Repository.BeneficiaryRepository;
import fr.openclassrooms.rayane.paymybuddy.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/** Class managing controllers routes related to Beneficiary */
@RestController
@RequestMapping("/beneficiary")
public class BeneficiaryController {

  private static Logger logger = LoggerFactory.getLogger(BeneficiaryController.class);

  @Autowired BeneficiaryRepository beneficiaryRepository;

  @Autowired UserRepository userRepository;

  /**
   * Route to add a beneficiary in database. BeneficiaryDto is a class representing and containing
   * the data sent and necessary for modifying the database
   *
   * @param beneficiary
   */
  @PostMapping("/add")
  public int addBeneficiary(@RequestBody BeneficiaryDto beneficiary) {
    logger.info("http://localhost:8080/beneficiary/add");
    try {
      return beneficiaryRepository.save(
          beneficiary.getUserSendingId(), beneficiary.getUserReceivingId());
    } catch (Exception e) {
      logger.error("We couldn't add a beneficiary: " + e);
      return 0;
    }
  }

  /**
   * Route to delete a beneficiary row in database. BeneficiaryDto is a class representing and
   * containing the data sent and necessary for modifying the database.
   *
   * @param beneficiary
   */
  @DeleteMapping("/delete")
  public int deleteBeneficiary(@RequestBody BeneficiaryDto beneficiary) {
    logger.info("http://localhost:8080/beneficiary/delete");
    try {
      return beneficiaryRepository.delete(
          beneficiary.getUserSendingId(), beneficiary.getUserReceivingId());
    } catch (Exception e) {
      logger.error("We couldn't delete this beneficiary: " + e);
      return 0;
    }
  }

  /**
   * @param id
   * @return the Beneficiary who has the id used in parameter
   */
  @GetMapping("/get/{id}")
  public Beneficiary getBeneficiaryById(@PathVariable int id) {
    try {
      logger.info("http://localhost:8080/beneficiary/get/" + id);
      return beneficiaryRepository.findById(id).get();
    } catch (Exception e) {
      logger.error("We couldn't find a beneficiary with this id");
      return null;
    }
  }

  /** @return a list of all beneficiaries who have the user defined in param by it's id */
  @GetMapping("/getAll")
  public ArrayList<Beneficiary> getUserBeneficiary(Authentication authentication) {
    try {
      logger.info("http://localhost:8080/beneficiary/getAll");
      return beneficiaryRepository.findAllByUserSendingId(
          userRepository.findUserByUsername(authentication.getName()).get());
    } catch (Exception e) {
      logger.error("We couldn't find a beneficiary with this id");
      return null;
    }
  }
}
