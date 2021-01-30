package com.github.flyaway2112.springboot.playground.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import com.github.flyaway2112.springboot.playground.service.UserService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class UsersRestControllerTest {

  private MockMvc mvc;

  private static final String USERNAME_PATH = "/users/{userId}/username";

  @BeforeEach
  public void beforeEach() {
    UserService userService = mock(UserService.class);
    doReturn(Optional.of("John Smith")).when(userService).getUsername("123");
    this.mvc = MockMvcBuilders.standaloneSetup(new UserRestController(userService)).build();
  }

  @Test
  public void ユーザーが見つかるとユーザー名を返す() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get(USERNAME_PATH, "123"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("John Smith"));
  }

  @Test
  public void ユーザーが見つからないと404を返す() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get(USERNAME_PATH, "124"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  public void POSTメソッドには405を返す() throws Exception {
    mvc.perform(MockMvcRequestBuilders.post(USERNAME_PATH, "123"))
        .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
  }

  @Test
  public void PUTメソッドには405を返す() throws Exception {
    mvc.perform(MockMvcRequestBuilders.put(USERNAME_PATH, "123"))
        .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed());
  }

}