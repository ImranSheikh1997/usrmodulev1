package com.usermodule.fileutil.service;


import com.usermodule.exceptionutil.CustomException;
import com.usermodule.fileutil.config.property.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;


@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new CustomException("Could not create the directory where the uploaded files will be stored.", HttpStatus.BAD_GATEWAY);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new CustomException("File Not Found", HttpStatus.NOT_FOUND);
            }
        } catch (MalformedURLException e) {
            throw new CustomException("File Not Found", HttpStatus.NOT_FOUND);
        }
    }


    public String storeFile(MultipartFile file) {
        //Normalize Name of file
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {

            checkInvalidCharacters(fileName);
            //copy file to the target location(Replacing filename with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException e) {
            throw new CustomException("Could not Store File", HttpStatus.BAD_REQUEST);
        }
    }

    //to check if filename contains any invalid characters
    public void checkInvalidCharacters(String fileName) {
        if (fileName.contains("..")) {
            throw new CustomException("FileName Contains invalid Characters" + fileName, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public Path loadFileAsPath(String fileName) {
        Path imagePath=null;
        Path rootLocation = Paths.get(fileName);
        try{
            if(rootLocation.toFile().exists()){
                Iterator<Path> iterator = Files.newDirectoryStream(rootLocation).iterator();
                if(iterator.hasNext()){
                    imagePath = iterator.next();
                }
            }
        }
        catch (IOException ie){
            throw new CustomException("Can't retrieve image",HttpStatus.BAD_REQUEST);
        }
        return imagePath;
    }
}