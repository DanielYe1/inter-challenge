package com.example.inter.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private List<CheckDigit> checkDigits;

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.checkDigits = new ArrayList<CheckDigit>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CheckDigit> getCheckDigits() {
        return Optional.ofNullable(checkDigits)
                .map(c -> new ArrayList(c))
                .orElse(new ArrayList<CheckDigit>());
    }

    public void addCheckDigitToUser(CheckDigit checkDigit) {
        if (this.checkDigits == null) {
            this.checkDigits = new ArrayList<CheckDigit>();
        }
        this.checkDigits.add(checkDigit);
    }
}
