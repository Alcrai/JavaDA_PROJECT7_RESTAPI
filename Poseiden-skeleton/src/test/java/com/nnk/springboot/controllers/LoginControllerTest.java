package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {
    @Mock
    private UserRepository userRepository;
    private LoginController loginController;

    @BeforeEach
    public void init(){
        loginController=new LoginController(userRepository);
    }

    @Test
    public void loginTest(){
        assertThat(loginController.login()).isInstanceOf(ModelAndView.class);
    }

    @Test
    public void getAllUserArticlesTest(){
        User user = new User("alex","alex","alex","USER");
        List<User> users= new ArrayList<>();
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);
        assertThat(loginController.getAllUserArticles()).isInstanceOf(ModelAndView.class);
        verify(userRepository).findAll();
    }
    @Test
    public void errorTest(){
        assertThat(loginController.error()).isInstanceOf(ModelAndView.class);
    }
}
