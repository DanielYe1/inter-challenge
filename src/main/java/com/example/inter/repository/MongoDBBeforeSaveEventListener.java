package com.example.inter.repository;

import com.example.inter.model.UserKey;
import com.example.inter.service.SecurityService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MongoDBBeforeSaveEventListener extends AbstractMongoEventListener<Object> {

    @Autowired
    PublicKeyRepository keyRepository;

    @Autowired
    SecurityService securityService;

    @Override
    public void onBeforeSave(BeforeSaveEvent<Object> event) {


        Document eventObject = event.getDocument();
        if (eventObject.get("_id") == null) {
            super.onBeforeSave(event);
            return;
        }
        Optional<UserKey> userKey = keyRepository.findById(((UserKey) eventObject.get("_id")).getPublicKeyString());
        if (!userKey.isPresent()) {
            super.onBeforeSave(event);
            return;
        }
        List<String> keysToEncrypt = Arrays.asList("_name", "_email");

        for (String key : eventObject.keySet()) {
            if (keysToEncrypt.contains(key)) {
                try {
                    eventObject.put(key, securityService.encrypt(eventObject.get(key).toString(),
                            userKey.map(UserKey::getPublicKeyString).get()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        super.onBeforeSave(event);
    }
}
