package com.usermodule.controller;

import com.usermodule.dto.LogInWithResponse;
import com.usermodule.dto.registration.RegistrationRequest;
import com.usermodule.model.enums.Gender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@CrossOrigin("*")
@Slf4j
public class FacebookController {

    String appId;
    String appSecret;
    @Autowired
    private LogInWithResponse logInWithResponse;

    private FacebookConnectionFactory factory=new FacebookConnectionFactory("209722797609529","72a17aad6afcc20b71fe465bf2d4d1c1");

    @Value("${facebook.redirect.url}")
    static String redirecturl;

    @GetMapping("/usefacebook")
    public ResponseEntity<?> produce(){

        OAuth2Operations operations = factory.getOAuthOperations();
        OAuth2Parameters params = new OAuth2Parameters();
        redirecturl=redirecturl+"/forwardLogin";
        params.setRedirectUri(redirecturl);
        params.setScope("email,public_profile");

        String url= operations.buildAuthenticateUrl(params);
        log.info("URL {}",params);
        return new ResponseEntity<>("redirect:"+url, HttpStatus.OK);
    }

    @GetMapping("/forwardlogin")
    public ResponseEntity<?> produceFbInfo(
            @RequestParam("code") String authorizationCode
    ){
        OAuth2Operations operations = factory.getOAuthOperations();
        AccessGrant accessToken = operations.exchangeForAccess(authorizationCode,redirecturl,null);
        Connection<Facebook> connection = factory.createConnection(accessToken);
        Facebook facebook = connection.getApi();
        String[] fields = {"email","firstName","lastName","gender"};
        User userProfile = facebook.fetchObject("me",User.class,fields);

        RegistrationRequest user=new RegistrationRequest(
                userProfile.getEmail(),
                UUID.randomUUID().toString(),
                userProfile.getFirstName(),
                userProfile.getLastName(),
                Gender.valueOf(userProfile.getGender())
        );
        logInWithResponse.signUp(user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
