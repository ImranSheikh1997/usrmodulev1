package com.usermodule.service;

import com.usermodule.entity.token.ConfirmationToken;
import com.usermodule.entity.user.User;
import com.usermodule.repository.UserRepository;
import com.usermodule.utility.exception.CustomException;
//import com.usermodule.utility.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private  static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

//    @Autowired
//    private AuthenticationManager authenticationManager;

//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public String signUpUser(User user){

        //to check is user is present or not (return type boolean)
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if(userExists){
            //TODO Check attributes(firstname,lastname etc are same
            //TODO and if email not confirmed then confirmation email.

            throw new CustomException("Email already taken", HttpStatus.BAD_REQUEST);
        }

  //      user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return emailOrMobileVerification(user);
    }

    public String emailOrMobileVerification(User user){
        //Generating random number for Email verification token and Sms Verification
        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public void facebookSignUp(User user){
        //TODO: if saving user to database then everytime hitting sign in with will give email already exists error
    }
    //for enabling user based on email
    public void enableUser(String email) {
        userRepository.enableUser(email);
    }

    public HttpStatus signin(String email, String password) {
//        try{
//        //    Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
//             List<Role> roles = Collections.singletonList(userRepository.findByEmail(email).get().getRole());
//            return "Ok";
//                    //jwtTokenProvider.createToken(email, roles);
//        }
//        catch (AuthenticationException e){
//            throw new CustomException("Invalid Username/Password supplied",HttpStatus.UNPROCESSABLE_ENTITY);
//        }
        return HttpStatus.OK;
    }

    public void delete(String email) {
        userRepository.deleteByEmail(email);
    }

    public Iterable<User> findAllUser() {
        return userRepository.findAll();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
