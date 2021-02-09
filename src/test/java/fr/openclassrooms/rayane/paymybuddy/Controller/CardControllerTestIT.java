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
public class CardControllerTestIT {
  @Autowired MockMvc mockMvc;

  @Test
  public void Put_AddMoney_ToUser() throws Exception {
    mockMvc
        .perform(
            put("/card/addMoney?amount={amount}", 1)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("rayane", "abcd"))
                .with(csrf()))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void Put_debitMoney_FromUser() throws Exception {
    mockMvc
        .perform(
            put("/card/debitMoney?amount={amount}", 1)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("rayane", "abcd"))
                .with(csrf()))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void Get_Card() throws Exception {
    mockMvc
        .perform(
            get("/beneficiary/get/{id}", 1)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("rayane", "abcd"))
                .contentType("application/json")
                .content("{\"number\":\"1234567891\", \"expirationDate\":\"2022-12-23\"}"))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void GetAll_OfUser() throws Exception {
    mockMvc
        .perform(
            get("/card/getAll")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("rayane", "abcd")))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void Delete_Card() throws Exception {
    mockMvc
        .perform(
            delete("/card/delete")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("rayane", "abcd"))
                .contentType("application/json")
                .content("{\"number\":\"1234567891\", \"expirationDate\":\"2022-12-23\"}")
                .with(csrf()))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void Add_Card() throws Exception {
    mockMvc
        .perform(
            post("/card/add")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("rayane", "abcd"))
                .contentType("application/json")
                .content("{\"number\":\"1234567891\", \"expirationDate\":\"2022-12-23\"}")
                .with(csrf()))
        .andExpect(status().is2xxSuccessful());
  }
}
