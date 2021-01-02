package com.udacity.jwdnd.course1.cloudstorage.services.user;

import com.udacity.jwdnd.course1.cloudstorage.exceptions.UsernameExistsException;
import com.udacity.jwdnd.course1.cloudstorage.model.User;

public interface UserService {
    int createNewUser(User user) throws UsernameExistsException;
}
