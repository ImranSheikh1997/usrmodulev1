package com.usermodule.dto.login;

import com.usermodule.config.BASE64DecodedMultipartFile;
import com.usermodule.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LogInResponse {
    @Autowired
    private FileStorageService fileStorageService;

    public String convertToMultipart(String base64) {
        MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipart(base64);
        return fileStorageService.storeFile(file);
    }
}
