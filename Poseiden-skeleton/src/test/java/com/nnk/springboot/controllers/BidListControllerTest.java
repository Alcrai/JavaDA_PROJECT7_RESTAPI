package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BidListControllerTest {
    @Autowired
    private MockMvc mockMvc;

 /*   @Test
    public void addBidFormTest() throws Exception {
        BidList bid = new BidList("Account Test", "Type Test", 10d);
        mockMvc.perform(get("/bidList/add")
                        .param("erroçraccount", "Account Test")
                        .param("errortype", "Type test"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"))
                .andExpect(model().attributeExists("erroraccount"))
                .andExpect(model().attributeExists("errortype"));
    }*/

}
