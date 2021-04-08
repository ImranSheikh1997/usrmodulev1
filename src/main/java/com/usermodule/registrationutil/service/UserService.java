package com.usermodule.registrationutil.service;

import com.usermodule.exceptionutil.CustomException;
import com.usermodule.jwtutil.JwtTokenProvider;
import com.usermodule.registrationutil.displayuserDTO.DisplayUserRequest;
import com.usermodule.registrationutil.entity.user.User;
import com.usermodule.registrationutil.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private  static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User signUpUser(User user) {
        checkEmail(user.getEmail());
        userRepository.save(user);
        return user;
    }
    public void checkEmail(String email)
    {
        Optional<User> userExists = userRepository.findByEmail(email);

        if(userExists.isPresent()){
            //TODO Check attributes(firstname,lastname etc are same
            //TODO and if email not confirmed then confirmation email.

            throw new CustomException("Email already taken", HttpStatus.BAD_REQUEST);
        }
    }
    public void facebookSignUp(User user){
        //TODO: if saving user to database then everytime hitting sign in with will give email already exists error
    }

    //for enabling user based on email
    public void enableUser(String email) {
        userRepository.enableUser(email);
    }

    public String signin(String email, String password) {
        try
        {
            Optional<User> user = userRepository.findByEmail(email);
            if(!user.isPresent()) {
                throw new CustomException("Invalid username supplied", HttpStatus.UNAUTHORIZED);
            }
            else if(user.get().isEmailVerified()){
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
                String token = jwtTokenProvider.createToken(email, Collections.singletonList(user.get().getRoles()));
                return token;
            }
            else{
                throw new CustomException("User is not verified yet",HttpStatus.UNAUTHORIZED);
            }
        }
        catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNAUTHORIZED);
        }
    }

    public void delete(String email) {
        userRepository.deleteByEmail(email);
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void updateUser(DisplayUserRequest displayUserRequest) {
        userRepository.updateUserByEmail(displayUserRequest.getEmail(),
                displayUserRequest.getCity(),
                displayUserRequest.getCountry(),
                displayUserRequest.getFirstName(),
                displayUserRequest.getLastName(),
                displayUserRequest.getGender(),
                displayUserRequest.getNumber(),
                displayUserRequest.getState()
        );
    }

    public boolean findByUserId(long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.get().isEmailVerified();
    }

    public void updatePassword(String email,String password) {

        userRepository.updatePassword(email,password);
    }
}
