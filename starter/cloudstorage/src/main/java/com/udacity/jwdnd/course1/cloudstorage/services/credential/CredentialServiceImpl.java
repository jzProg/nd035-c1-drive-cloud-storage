package com.udacity.jwdnd.course1.cloudstorage.services.credential;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialServiceImpl implements CredentialService {

    @Autowired
    private CredentialMapper credentialMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private EncryptionService encryptionService;

    @Override
    public List<Credential> fetchAllCredentials(String username) {
        User user = userService.getUser(username);
        return credentialMapper.getCredentials(user.getUserId());
    }

    @Override
    public int deleteCredential(Integer credentialId) {
        return credentialMapper.deleteCredential(credentialId);
    }

    @Override
    public int addCredential(Credential credential, String username) {
        if (credential.getCredentialid() != null) {
            credential.setPassword(encryptionService.encryptValue(credential.getPassword(), credential.getKey()));
            return credentialMapper.editCredential(credential);
        }
        credential.setKey(getKey());
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), credential.getKey()));
        User user = userService.getUser(username);
        credential.setUserid(user.getUserId());
        return credentialMapper.insertCredential(credential);
    }

    private String getKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }
}
