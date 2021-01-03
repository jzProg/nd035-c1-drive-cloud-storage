package com.udacity.jwdnd.course1.cloudstorage.services.file;

import com.udacity.jwdnd.course1.cloudstorage.exceptions.FileExistsException;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private UserService userService;


    @Override
    public File getFile(String fileName, String username) {
        User user = userService.getUser(username);
        return fileMapper.getFile(fileName, user.getUserId());
    }

    @Override
    public List<File> getFiles(String username) {
        User user = userService.getUser(username);
        return fileMapper.getFiles(user.getUserId());
    }

    @Override
    public int upload(MultipartFile file, String username) throws FileExistsException, IOException {
        User user = userService.getUser(username);
        Integer userId = user.getUserId();
        String fileName = file.getOriginalFilename();
        if (fileMapper.getFile(fileName, userId) != null) {
            throw new FileExistsException(fileName + " already exists...", null);
        }
        File fileObj = new File(null, fileName, file.getContentType(), String.valueOf(file.getSize()), userId, file.getBytes());
        return fileMapper.uploadFile(fileObj);
    }

    @Override
    public int deleteFile(Integer fileId) {
        return fileMapper.deleteFile(fileId);
    }
}
