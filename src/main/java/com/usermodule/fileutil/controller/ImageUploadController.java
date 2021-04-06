package com.usermodule.fileutil.controller;

import com.usermodule.fileutil.dto.UploadFileResponse;
import com.usermodule.fileutil.dto.image.ImageRequest;
import com.usermodule.fileutil.service.FileStorageService;
import com.usermodule.registrationutil.dto.login.FileStorageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
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
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@RestController
@CrossOrigin("*")
public class ImageUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private FileStorageResponse fileStorageResponse;

    //This API is for Uploading A file.
    @PostMapping("/registration/uploadFile/multipart")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file
    )
    {
        String filename = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadimage/")
                .path(filename)
                .toUriString();

        return new ResponseEntity<>(new UploadFileResponse(filename, fileDownloadUri, file.getContentType(), file.getSize()),HttpStatus.OK);
      //  return new ResponseEntity<>(filename, HttpStatus.OK);
    }

    @PostMapping("/registration/uploadfile/base64")
    public ResponseEntity<?> uploadBase64File(
            @RequestBody ImageRequest imageRequest
           // @RequestParam("avatar") String BASE64
    ){

        String filename = fileStorageResponse.convertToMultipart(imageRequest.getAvatar());
        //String filename = fileStorageService.storeFile(imageRequest);
        return new ResponseEntity<>(filename,HttpStatus.ACCEPTED);
    }

    @GetMapping("/displayimage/{fileName:.+}")
    public ResponseEntity<Resource> displayImage(
            @PathVariable String fileName)  {

        try {
            Path imagePath = fileStorageService.loadFileAsPath(fileName);

            if (imagePath != null) {
                ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(imagePath));

                return ResponseEntity
                        .ok()
                        .contentLength(imagePath.toFile().length())
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //This Api is for Downloading file
    @GetMapping("/downloadimage/{fileName:.+}")
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
