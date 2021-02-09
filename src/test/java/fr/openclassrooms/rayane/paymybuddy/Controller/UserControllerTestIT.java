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
public class UserControllerTestIT {
  @Autowired MockMvc mockMvc;

  @Test
  public void Deactivate_User_Enabled() throws Exception {
    mockMvc
        .perform(
            put("/user/deactivate/{id}", 1)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("rayane", "abcd"))
                .with(csrf()))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void Add_User_InDB() throws Exception {
    mockMvc
        .perform(
            post("/user/add")
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("rayane", "abcd"))
                .contentType("application/json")
                .content(
                    "{\"username\":\"Fred\", \"email\":\"fred@gmail.com\", \"password\":\"abcdef\", \"money\":100, \"enabled\":true, \"role\":\"USER\"}")
                .with(csrf()))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void Get_User_FromDB() throws Exception {
    mockMvc
        .perform(
            get("/user/get/{id}", 2)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("rayane", "abcd")))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void Get_Beneficiary() throws Exception {
    mockMvc
        .perform(
            get("/beneficiary/get/{id}", 1)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("rayane", "abcd")))
        .andExpect(status().is2xxSuccessful());
  }
}
