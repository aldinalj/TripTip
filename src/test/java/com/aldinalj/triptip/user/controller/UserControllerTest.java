package com.aldinalj.triptip.user.controller;

import com.aldinalj.triptip.user.authorities.UserRole;
import com.aldinalj.triptip.user.model.CustomUser;
import com.aldinalj.triptip.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

    private final MockMvc mockMvc;
    private final UserRepository userRepository;

    @Autowired
    public UserControllerTest(MockMvc mockMvc, UserRepository userRepository) {
        this.mockMvc = mockMvc;
        this.userRepository = userRepository;
    }

    @BeforeEach
    void setInitialUser() {

        CustomUser user = new CustomUser(
                "John",
                "JohnDoe",
                "blueberrypie",
                UserRole.USER,
                true,
                true,
                true,
                true
        );

        userRepository.save(user);
    }

    @Test
    void registerUser() throws Exception {
        mockMvc.perform(post("/register")
                .contentType("application/json")
                .content("""
                        {
                            "display_name": "Jane",
                            "username": "JaneSmith78",
                            "password": "applepie12"
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.display_name").value("Jane"))
                .andExpect(jsonPath("$.username").value("JaneSmith78"));
    }

    @Test
    void registerAlreadyRegisteredUser() throws Exception {
        mockMvc.perform(post("/register")
                        .contentType("application/json")
                        .content(""" 
                                {
                                  "display_name": "Johnny",
                                  "username": "JohnDoe",
                                  "password": "pumpkinpie"
                                }
                                """))
                .andExpect(status().isConflict());
    }


}