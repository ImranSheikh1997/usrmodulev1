package com.usermodule.fileutil.service;

import com.usermodule.exceptionutil.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

class FileStorageServiceTest {

    @Mock
    private Path fileStorageLocation;

    @Mock
    private MultipartFile multipartFile;

    @BeforeEach
    void setUp() {
    }

    @Test
    void is_File_loaded_As_Resource() throws MalformedURLException {
        String fileName = "abc.png";
        //Actual
        Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
        //Given
        Resource resource = new UrlResource(filePath.toUri());
        //then
        assert(resource.exists());
    }

    @Test
    void Is_File_Stored_Successfully() throws IOException {
        String fileName = "abc.png";
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        given(Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING)).willThrow(
                new CustomException("can't store file", HttpStatus.BAD_GATEWAY)
        );
    }

    @Test
    void checkInvalidCharacters() {
        String filename = "cdsj..txt";
        assertThat(filename, containsString(".."));
    }
}