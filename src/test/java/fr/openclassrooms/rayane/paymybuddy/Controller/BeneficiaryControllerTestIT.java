package fr.openclassrooms.rayane.paymybuddy.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class BeneficiaryControllerTestIT {
  @Autowired MockMvc mockMvc;

  @Test
  public void Get_Beneficiary() throws Exception {
    mockMvc
        .perform(
            get("/beneficiary/get/{id}", 1)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("rayane", "abcd")))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void GetAll_Beneficiaries() throws Exception {
    mockMvc
        .perform(
            get("/beneficiary/getAll?userId={id}", 1)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("rayane", "abcd")))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void Delete_Beneficiaries() throws Exception {
    mockMvc
        .perform(
            delete("/beneficiary/delete")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("rayane", "abcd"))
                .contentType("application/json")
                .content("{\"userReceivingId\": 1, \"userSendingId\": 4}")
                .with(csrf()))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void Add_Beneficiary() throws Exception {
    mockMvc
        .perform(
            post("/beneficiary/add")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("rayane", "abcd"))
                .contentType("application/json")
                .content("{\"userReceivingId\": 6, \"userSendingId\": 1}")
                .with(csrf()))
        .andExpect(status().is2xxSuccessful());
  }
}
