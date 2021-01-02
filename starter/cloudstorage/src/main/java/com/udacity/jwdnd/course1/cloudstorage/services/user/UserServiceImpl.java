package com.udacity.jwdnd.course1.cloudstorage.services.user;

import com.udacity.jwdnd.course1.cloudstorage.exceptions.UsernameExistsException;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private HashService hashService;

    @Override
    public User getUser(String username) {
        return userMapper.getUser(username);
    }

    @Override
    public int createNewUser(User user) throws UsernameExistsException {
        if (!isUsernameAvailable(user.getUsername())) {
            throw new UsernameExistsException("Username exists...Please use another one", null);
        }
        hashPassword(user);
        return userMapper.insert(user);
    }

    private boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    private void hashPassword(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        user.setSalt(encodedSalt);
        user.setPassword(hashService.getHashedValue(user.getPassword(), encodedSalt));
    }
}
