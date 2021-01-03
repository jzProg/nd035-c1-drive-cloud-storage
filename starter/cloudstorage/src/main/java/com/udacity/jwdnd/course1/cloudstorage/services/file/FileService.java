package com.udacity.jwdnd.course1.cloudstorage.services.file;

import com.udacity.jwdnd.course1.cloudstorage.exceptions.FileExistsException;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    File getFile(String fileName, String username);
    List<File> getFiles(String username);
    int upload(MultipartFile file, String username) throws FileExistsException, IOException;
    int deleteFile(Integer fileId);
}
