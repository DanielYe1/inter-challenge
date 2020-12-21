package com.example.inter.controller;

import com.example.inter.controller.DTO.RequestDTO;
import com.example.inter.controller.DTO.UserDTO;
import com.example.inter.model.CheckDigit;
import com.example.inter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.inter.service.UserService;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listAll() {
        return new ResponseEntity(userService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity saveUser(@RequestBody UserDTO user, @RequestHeader("public-key") String publicKey) {
        try {
            User added = userService.add(user, publicKey);
        } catch (IllegalBlockSizeException | InvalidKeyException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity searchUser(@PathVariable("id") String userId) {
        Optional<User> user = userService.findById(userId);
        if (user.isPresent()) {
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@PathVariable("id") String id, @RequestBody UserDTO user, @RequestHeader("public-key") String publicKey) {
        if (userService.update(id, user, publicKey)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable("id") String id) {
        userService.delete(id);
        return new ResponseEntity((HttpStatus.NO_CONTENT));
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ResponseEntity calculateCheckDigit(@RequestBody RequestDTO digitValues) {
        CheckDigit checkDigit = userService.addCheckDigitToUser(digitValues.getUserId(), digitValues.getN(), digitValues.getK());
        return new ResponseEntity(checkDigit, HttpStatus.OK);
    }
}
