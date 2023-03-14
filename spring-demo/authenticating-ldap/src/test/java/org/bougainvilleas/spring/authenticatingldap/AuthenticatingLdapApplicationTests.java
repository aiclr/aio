package org.bougainvilleas.spring.authenticatingldap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticatingLdapApplicationTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void loginWithValidUserThenAuthenticated() throws Exception {
    SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login = formLogin()
            .user("ben")
            .password("benspassword");

    mockMvc.perform(login)
            .andExpect(authenticated().withUsername("ben"));
  }

  @Test
  public void loginWithInvalidUserThenUnauthenticated() throws Exception {
    SecurityMockMvcRequestBuilders.FormLoginRequestBuilder login = formLogin()
            .user("invalid")
            .password("invalidpassword");

    mockMvc.perform(login)
            .andExpect(unauthenticated());
  }

  @Test
  public void getPassword() throws Exception {
    System.err.println(new BCryptPasswordEncoder().encode("benspassword"));
    Assertions.assertTrue(new BCryptPasswordEncoder().matches("benspassword","$2a$10$6GWksKObP2zUqCrRnjpJiuTLl2Fzp5R7P2lDN.tR75TIba39Uifh6"));
    Assertions.assertTrue(new BCryptPasswordEncoder().matches("benspassword","$2a$10$c6bSeWPhg06xB1lvmaWNNe4NROmZiSpYhlocU/98HNr2MhIOiSt36"));
  }
}
