package com.example.inter.service;

import com.example.inter.controller.DTO.UserDTO;
import com.example.inter.model.CheckDigit;
import com.example.inter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.inter.repository.UserRepository;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private DigitCalculatorService digitCalculatorService;

    @Autowired
    private SecurityService securityService;

    public User add(UserDTO user, String publicKey) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        User applicationUser = new User(securityService.encrypt(user.getName(), publicKey),
                securityService.encrypt(user.getEmail(), publicKey));
        User inserted = repository.insert(applicationUser);
        return inserted;
    }

    public boolean delete(String id) {
        boolean exists = repository.existsById(id);
        if (exists) {
            repository.deleteById(id);
        }
        return exists;
    }

    public boolean update(String id, UserDTO user, String publicKey) {
        AtomicBoolean updated = new AtomicBoolean(false);
        repository.findById(id).map(u -> {
            try {
                u.setName(securityService.encrypt(user.getName(), publicKey));
                u.setEmail(securityService.encrypt(user.getEmail(), publicKey));
            } catch (Exception e) {
                e.printStackTrace();
            }
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