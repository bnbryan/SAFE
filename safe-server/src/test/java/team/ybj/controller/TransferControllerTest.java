package team.ybj.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    public void testSuccessfulTransfer() throws Exception {
        String successfulResultJson = "{\"fromAccountNum\": 18,\"fromAccountType\": \"C\",\"toAccountNum\": 1,\"amount\": \"100.00\"}";
        mockMvc.perform(post("/transactions/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(successfulResultJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.msg").value("transfer success"));

        String reverseSuccessfulResultJson = "{\"fromAccountNum\": 1,\"fromAccountType\": \"C\",\"toAccountNum\": 18,\"amount\": \"100.00\"}";
        mockMvc.perform(post("/transactions/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reverseSuccessfulResultJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.msg").value("transfer success"));
    }


    @Test
    public void testFailedTransfer() throws Exception {
        String successfulResultJson = "{\"fromAccountNum\": 1,\"fromAccountType\": \"C\",\"toAccountNum\": 18,\"amount\": \"100000.00\"}";
        mockMvc.perform(post("/transactions/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(successfulResultJson))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.code").value(422))
                .andExpect(jsonPath("$.msg").value("transfer fail"));
    }





}
