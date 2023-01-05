package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserRepository userRepository;
    private UserController userController;
    @Mock
    private Model model;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private RedirectAttributes redirectAttributes;
    private User user;
    private List<User> userList;

    @BeforeEach
    public void init(){
        userController = new UserController(userRepository);
        user = new User("alex","aA1$aZ","alex","USER");
        user.setId(1);
        userList = new ArrayList<>();
        userList.add(user);
    }

    @Test
    public void homeTest(){
        when(userRepository.findAll()).thenReturn(userList);
        assertThat(userController.home(model)).isEqualTo("user/list");
        verify(userRepository).findAll();
    }

    @Test
    public void addUserTest(){
        assertThat(userController.addUser(user,"",model)).isEqualTo("user/add");
    }

    @Test
    public void validateTest(){
        User userNotPassGood = new User("alex","alex","alex","USER");
        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findAll()).thenReturn(userList);
        assertThat(userController.validate(user,bindingResult,model,redirectAttributes)).isEqualTo( "redirect:/user/list");
        assertThat(userController.validate(userNotPassGood,bindingResult,model,redirectAttributes)).isEqualTo( "redirect:/user/add");
        verify(userRepository).save(user);
        verify(userRepository).findAll();
    }

    @Test
    public void showUpdateFormTest(){
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        assertThat(userController.showUpdateForm(1,"",model)).isEqualTo("user/update");
        verify(userRepository).findById(1);
    }

    @Test
    public void updateUserTest(){
        User userNotPassGood = new User("alex","alex","alex","USER");
        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findAll()).thenReturn(userList);
        assertThat(userController.updateUser(1,user,bindingResult,model,redirectAttributes)).isEqualTo( "redirect:/user/list");
        assertThat(userController.updateUser(1,userNotPassGood,bindingResult,model,redirectAttributes)).isEqualTo( "redirect:/user/update");
        verify(userRepository).save(user);
        verify(userRepository).findAll();
    }

    @Test
    public void deleteUserTest(){
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.findAll()).thenReturn(userList);
        assertThat(userController.deleteUser(1,model)).isEqualTo("redirect:/user/list");
        verify(userRepository).findById(1);
        verify(userRepository).findAll();
    }

}
