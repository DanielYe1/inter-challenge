package controller;

import model.CheckDigit;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listAll(){
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
    public ResponseEntity calculateCheckDigit(String userId, Integer n, Integer k){
        CheckDigit checkDigit = new CheckDigit(n,k);
        return new ResponseEntity(checkDigit.calculateCheckDigit(), HttpStatus.OK);
    }
}
