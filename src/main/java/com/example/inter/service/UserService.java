package com.example.inter.service;

import com.example.inter.model.CheckDigit;
import com.example.inter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.inter.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public User add(User User) {
        User inserted = repository.insert(User);
        return inserted;
    }

    public boolean delete(String id) {
        boolean exists = repository.existsById(id);
        if (exists) {
            repository.deleteById(id);
        }
        return exists;
    }

    public boolean update(String id, User User) {
        boolean exists = repository.existsById(id);
        if (exists) {
            User.setId(id);
            repository.save(User);
        }
        return exists;
    }

    public Optional<User> findById(String id) {
        return repository.findById(id);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public CheckDigit addCheckDigitToUser(String userId, String value, Integer numberTimes) {
        CheckDigit checkDigit = new CheckDigit(value, numberTimes);
        checkDigit.calculateCheckDigit();

        Optional<User> user = findById(userId);
        user.ifPresent(u -> u.addCheckDigitToUser(checkDigit));

        return checkDigit;
    }
}