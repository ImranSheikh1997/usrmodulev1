package com.usermodule.controller;

import com.usermodule.dto.UploadFileResponse;
import com.usermodule.dto.image.ImageRequest;
import com.usermodule.dto.login.LogInResponse;
import com.usermodule.service.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RestController
@CrossOrigin("*")
public class ImageUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private LogInResponse logInResponse;

    //This API is for Uploading A file.
    @PostMapping("/registration/uploadFile/multipart")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file
    )
    {
        String filename = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(filename)
                .toUriString();

        return new ResponseEntity<>(new UploadFileResponse(filename, fileDownloadUri, file.getContentType(), file.getSize()),HttpStatus.OK);
      //  return new ResponseEntity<>(filename, HttpStatus.OK);
    }

    @PostMapping("/registration/uploadfile/base64")
    public ResponseEntity<?> uploadBase64File(
            //@RequestBody ImageRequest imageRequest
            @RequestParam("avatar") String BASE64){

        String filename = logInResponse.convertToMultipart(BASE64);
        //String filename = fileStorageService.storeFile(imageRequest);
        return new ResponseEntity<>(filename,HttpStatus.ACCEPTED);
    }

    //This Api is for Downloading file
    @GetMapping("/multipart/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable String fileName,
            HttpServletRequest request){

        //Load File As Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        //Try to determine file's content type
        String contentType = null;

        try{
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }
        catch (IOException e){
            log.info("Could not determine file type.");
        }

        //fallback to default content type if type could not be determined
        if(contentType == null){
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ resource.getFilename() + "\"")
                .body(resource);
    }

}
