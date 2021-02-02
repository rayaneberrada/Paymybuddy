package fr.openclassrooms.rayane.paymybuddy.Controller;

import fr.openclassrooms.rayane.paymybuddy.DTO.BeneficiaryDto;
import fr.openclassrooms.rayane.paymybuddy.Entity.Beneficiary;
import fr.openclassrooms.rayane.paymybuddy.Repository.BeneficiaryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/** Class managing controllers routes related to Beneficiary */
@RestController
@RequestMapping("/beneficiary")
public class BeneficiaryController {

  private static Logger logger = LoggerFactory.getLogger(BeneficiaryController.class);

  @Autowired BeneficiaryRepository beneficiaryRepository;

  @PostMapping("/add")
  public void addBeneficiary(@RequestBody BeneficiaryDto beneficiary) {
    logger.info("http://localhost:8080/beneficiary/add");
    System.out.println(beneficiary.getUserReceivingId() + beneficiary.getUserSendingId());
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    Beneficiary beneficiaryEntity = modelMapper.map(beneficiary, Beneficiary.class);
    System.out.println(beneficiaryEntity);
    // return beneficiaryRepository.save(new Beneficiary(beneficiary));
  }

  @DeleteMapping("/delete")
  public void deleteBeneficiary(@RequestBody Beneficiary beneficiary) {
    logger.info("http://localhost:8080/beneficiary/delete");
    beneficiaryRepository.delete(beneficiary);
  }
}
