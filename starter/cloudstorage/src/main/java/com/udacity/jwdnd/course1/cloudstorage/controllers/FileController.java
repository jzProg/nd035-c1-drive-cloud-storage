package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.exceptions.FileExistsException;
import com.udacity.jwdnd.course1.cloudstorage.services.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("api/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public String upload(@RequestParam("fileUpload") MultipartFile file, Model model, Authentication authentication) {
        boolean error = false;
        try {
            int rows = fileService.upload(file, authentication.getName());
            if (rows < 0) {
                error = true;
            }
        } catch (FileExistsException e) {
            error = true;
            model.addAttribute("errorMessage", e.getMessage());
        } catch (IOException e) {
            error = true;
        }
        model.addAttribute("error", error);
        return "result";
    }

    @GetMapping("/deleteFile")
    public String deleteFile(@RequestParam(value = "fileId") Integer fileId, Model model) {
        int rows = fileService.deleteFile(fileId);
        boolean error = rows < 0;
        model.addAttribute("error", error);
        return "result";
    }

    @GetMapping(value = "/getFile/{fileName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody byte[] getFile(@PathVariable String fileName, Authentication authentication) {
        return fileService.getFile(fileName, authentication.getName()).getFileData();
    }
}
