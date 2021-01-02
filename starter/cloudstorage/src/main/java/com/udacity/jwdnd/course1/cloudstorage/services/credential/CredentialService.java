package com.udacity.jwdnd.course1.cloudstorage.services.credential;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;

import java.util.List;

public interface CredentialService {
    List<Credential> fetchAllCredentials(String username);
    int deleteCredential(Integer credentialId);
    int addCredential(Credential credential, String username);
}
