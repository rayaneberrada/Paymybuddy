package fr.openclassrooms.rayane.paymybuddy.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class TransactionControllerTestIT {
  @Autowired MockMvc mockMvc;

  @Test
  public void Put_SendMoney_FromUserToUser() throws Exception {
    mockMvc
        .perform(
            put("/transaction/transfer")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("rayane", "abcd"))
                .contentType("application/json")
                .content("{\"receivingId\":4, \"amount\" : 200}")
                .with(csrf()))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void Get_Transaction() throws Exception {
    mockMvc
        .perform(
            get("/transaction/get/{id}", 2)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("rayane", "abcd")))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void GetAll_UserTransaction() throws Exception {
    mockMvc
        .perform(
            get("/transaction/getAll?userId={id}", 2)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("rayane", "abcd")))
        .andExpect(status().is2xxSuccessful());
  }
}
