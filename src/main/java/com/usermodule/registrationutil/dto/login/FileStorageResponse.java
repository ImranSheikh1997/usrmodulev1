package com.usermodule.registrationutil.dto.login;

import com.usermodule.fileutil.config.BASE64DecodedMultipartFile;
import com.usermodule.fileutil.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageResponse {
    @Autowired
    private FileStorageService fileStorageService;

    public String convertToMultipart(String avatar) {
        MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipart(avatar);
        return fileStorageService.storeFile(file);
    }
}
