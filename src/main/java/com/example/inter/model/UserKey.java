package com.example.inter.model;

public class UserKey {
    String userId;
    String publicKeyString;

    public UserKey(String userId, String publicKeyString) {
        this.userId = userId;
        this.publicKeyString = publicKeyString;
    }

    public String getUserId() {
        return userId;
    }

    public String getPublicKeyString() {
        return publicKeyString;
    }
}
