package com.example.inter.service;

import com.example.inter.controller.DTO.UserDTO;
import com.example.inter.model.CheckDigit;
import com.example.inter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.inter.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    DigitCalculatorService digitCalculatorService;

    @Autowired
    SecurityService securityService;

    public User add(UserDTO User) {
        User inserted = repository.insert(User.toApplicationUser());
        return inserted;
    }

    public boolean delete(String id) {
        boolean exists = repository.existsById(id);
        if (exists) {
            repository.deleteById(id);
        }
        return exists;
    }

    public boolean update(String id, UserDTO user) {
        AtomicBoolean updated = new AtomicBoolean(false);
        repository.findById(id).map(u -> {
            u.setName(user.getName());
            u.setEmail(user.getEmail());
            return u;
        }).ifPresent(c -> {
            repository.save(c);
            updated.set(true);
        });

        return updated.get();
    }

    public Optional<User> findById(String id) {
        return repository.findById(id);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public CheckDigit addCheckDigitToUser(String userId, String value, Integer numberTimes) {
        Integer sumDigit = digitCalculatorService.calculateSumDigit(value, numberTimes);
        CheckDigit checkDigit = new CheckDigit(value, numberTimes, sumDigit);

        Optional<User> user = findById(userId);
        user.ifPresent(u -> {
            u.addCheckDigitToUser(checkDigit);
            repository.save(u);
        });

        return checkDigit;
    }

}