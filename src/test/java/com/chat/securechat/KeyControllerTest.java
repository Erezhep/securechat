package com.chat.securechat;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(KeyControllerTest.class)
public class KeyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnPublicKey() throws Exception {
        mockMvc.perform(get("/api/public-key"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value("su41QyZE+a/Fozl1R8kZlxFZjQVPFQ3C+PcMJGBPMHk="));
    }

}
