package fr.openclassrooms.rayane.paymybuddy.Controller;

import fr.openclassrooms.rayane.paymybuddy.DTO.BeneficiaryDto;
import fr.openclassrooms.rayane.paymybuddy.Entity.Beneficiary;
import fr.openclassrooms.rayane.paymybuddy.Repository.BeneficiaryRepository;
import fr.openclassrooms.rayane.paymybuddy.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
  public void addBeneficiary(@RequestBody BeneficiaryDto beneficiary) {
    logger.info("http://localhost:8080/beneficiary/add");
    try {
      beneficiaryRepository.save(beneficiary.getUserSendingId(), beneficiary.getUserReceivingId());
    } catch (Exception e) {
      logger.error("We couldn't add a beneficiary: " + e);
    }
    // ajouter une exception si insert ne renvoie pas 1 à rowAdded?
  }

  /**
   * Route to delete a beneficiary row in database. BeneficiaryDto is a class representing and
   * containing the data sent and necessary for modifying the database.
   *
   * @param beneficiary
   */
  @DeleteMapping("/delete")
  public void deleteBeneficiary(@RequestBody BeneficiaryDto beneficiary) {
    logger.info("http://localhost:8080/beneficiary/delete");
    try {
      beneficiaryRepository.delete(
          beneficiary.getUserSendingId(), beneficiary.getUserReceivingId());
    } catch (Exception e) {
      logger.error("We couldn't delete this beneficiary: " + e);
    }
  }

  /**
   * @param id
   * @return the Beneficiary who has the id used in parameter
   */
  @GetMapping("/get/{id}")
  public Beneficiary getBeneficiaryById(@PathVariable int id) {
    logger.info("http://localhost:8080/beneficiary/get/" + id);
    return beneficiaryRepository.findById(id).get();
  }

  /**
   * @param userId
   * @return a list of all beneficiaries who have the user defined in param by it's id
   */
  @GetMapping("/getAll")
  public ArrayList<Beneficiary> getUserBeneficiary(int userId) {
    logger.info("http://localhost:8080/beneficiary/getAll?userId=" + userId);
    return beneficiaryRepository.findAllByUserSendingId(userRepository.findById(userId).get());
  }
}
