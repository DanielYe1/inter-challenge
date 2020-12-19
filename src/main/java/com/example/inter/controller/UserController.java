package com.example.inter.controller;

import com.example.inter.controller.DTO.RequestDTO;
import com.example.inter.model.CheckDigit;
import com.example.inter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.inter.service.UserService;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listAll() {
        return new ResponseEntity(service.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity saveUser(@RequestBody User user) {
        User added = service.add(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity searchUser(@PathVariable("id") String UserId) {
        Optional<User> User = service.findById(UserId);
        if (User.isPresent()) {
            return new ResponseEntity<User>(User.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@PathVariable("id") String id, @RequestBody User User) {
        if (service.update(id, User)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ResponseEntity calculateCheckDigit(@RequestBody RequestDTO digitValues) {
        CheckDigit checkDigit = service.addCheckDigitToUser(digitValues.getUserId(), digitValues.getN(), digitValues.getK());
        return new ResponseEntity(checkDigit, HttpStatus.OK);
    }
}
