package com.nnk.springboot.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class HomeControllerTest {
    @Mock
    private Model model;
    private HomeController homeController = new HomeController();

    @Test
    public void homeTest(){
        assertThat(homeController.home(model)).isEqualTo("home");
    }
    @Test
    public void adminHomeTest(){
        assertThat(homeController.adminHome(model)).isEqualTo("redirect:/bidList/list");
    }

}
