package com.example.inter.model;

public class UserKey {
    String userId;
    String publicKey;

    public UserKey(String userId, String publicKey) {
        this.userId = userId;
        this.publicKey = publicKey;
    }

    public String getUserId() {
        return userId;
    }

    public String getPublicKey() {
        return publicKey;
    }
}
