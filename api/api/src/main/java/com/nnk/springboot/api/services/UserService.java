package com.nnk.springboot.api.services;

import com.nnk.springboot.api.domain.User;
import com.nnk.springboot.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public User update(User user, Integer id) {
        user.setId(id);
        return userRepository.save(user);
    }

    public User delete(Integer id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
        return user;
    }
}
