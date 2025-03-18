package programmerzamannow.springmvc.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class PartnerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getPartner() throws Exception {
        mockMvc.perform(
                get("/partner/current")
                        .header("X-API-KEY", "sample")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("sample : Sample partner"))
        );
    }
}