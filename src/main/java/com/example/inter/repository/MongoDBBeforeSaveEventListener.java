package com.example.inter.repository;

import com.example.inter.model.UserKey;
import com.example.inter.service.SecurityService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class MongoDBBeforeSaveEventListener extends AbstractMongoEventListener<Object> {

    @Autowired
    PublicKeyRepository keyRepository;

    @Autowired
    SecurityService securityService;

    @Override
    public void onBeforeSave(BeforeSaveEvent<Object> event) {

        Document eventObject = event.getDocument();

        List<String> keysToEncrypt = Arrays.asList("_nome", "_email");

        for (String key : eventObject.keySet()) {
            if (keysToEncrypt.contains(key)) {
                try {
                    eventObject.put(key, securityService.encrypt(eventObject.get(key).toString(),
                            keyRepository.findById(((UserKey) eventObject.get("_id")).getPublicKey())
                                    .map(UserKey::getPublicKey).get()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        super.onBeforeSave(event);
    }
}
