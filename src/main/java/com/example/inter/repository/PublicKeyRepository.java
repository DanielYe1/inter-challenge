package com.example.inter.repository;

import com.example.inter.model.UserKey;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PublicKeyRepository extends MongoRepository<UserKey, String> {

    Optional<UserKey> findFirstByUserId(String userKey);
}
