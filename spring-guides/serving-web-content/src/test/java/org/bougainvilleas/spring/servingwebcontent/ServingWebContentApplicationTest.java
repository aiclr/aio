package org.bougainvilleas.spring.servingwebcontent;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = GreetingController.class)
@TestPropertySource(properties = "logging.level.org.springframework.web=DEBUG")
public class ServingWebContentApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/index.html")
    public void homePage() throws Exception {
        // N.B. jsoup can be useful for asserting HTML content
        mockMvc.perform(get("/index.html"))
                .andExpect(content().string(containsString("Get your greeting")));
    }

    @Test
    @DisplayName("/greeting")
    public void greeting() throws Exception {
        mockMvc.perform(get("/greeting"))
                .andExpect(content().string(containsString("Hello, World!")));
    }

    @Test
    @DisplayName("/greeting?name=Greg")
    public void greetingWithUser() throws Exception {
        mockMvc.perform(get("/greeting").param("name", "Greg"))
                .andExpect(content().string(containsString("Hello, Greg!")));
    }

    @Test
    @DisplayName("GET /greeting-form")
    public void rendersForm() throws Exception {
        mockMvc.perform(get("/greeting-form"))
                .andExpect(content().string(containsString("Form")));
    }

    @Test
    @DisplayName("POST /greeting-form")
    public void submitsForm() throws Exception {
        mockMvc.perform(post("/greeting-form").param("id", "12345").param("content", "Hello"))
                .andExpect(content().string(containsString("Result")))
                .andExpect(content().string(containsString("id: 12345")));
    }

}
