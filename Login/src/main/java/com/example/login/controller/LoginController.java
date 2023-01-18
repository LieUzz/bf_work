package com.example.login.controller;

import com.example.login.domain.request.LoginRequest;
import com.example.login.domain.request.RegisterRequest;
import com.example.login.domain.response.LoginResponse;
import com.example.login.domain.response.RegisterResponse;
import com.example.login.entity.Permission;
import com.example.login.entity.User;
import com.example.login.exception.InvalidCredentialsException;
import com.example.login.security.AuthUserDetail;
import com.example.login.security.JwtProvider;
import com.example.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class LoginController {

    private AuthenticationManager authenticationManager;
    private UserService userService;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    private JwtProvider jwtProvider;

    @Autowired
    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    //User trying to log in with username and password
    @PostMapping("auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) throws InvalidCredentialsException {

        Authentication authentication;

        //Try to authenticate the user using the username and password
        try{
//            PasswordEncoder encoder = new BCryptPasswordEncoder();
//            System.out.println(request.getPassword());
//            String password = encoder.encode(request.getPassword());
//            System.out.println(password);
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (AuthenticationException e){
            throw new InvalidCredentialsException("Incorrect credentials, please try again");
        }
        //Successfully authenticated user will be stored in the authUserDetail object
        AuthUserDetail authUserDetail = (AuthUserDetail) authentication.getPrincipal(); //getPrincipal() returns the user object

        //A token wil be created using the username/email/userId and permission
        String token = jwtProvider.createToken(authUserDetail);

        //Returns the token as a response to the frontend/postman
        return ResponseEntity.ok(LoginResponse.builder()
                .message("Welcome " + authUserDetail.getUsername())
                .token(token)
                .build());

    }


    @PostMapping("register")
    public ResponseEntity<RegisterResponse> register(
            @Valid @RequestBody RegisterRequest request,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(error -> System.out.println(
                    "ValidationError in " + error.getObjectName() + ": " + error.getDefaultMessage()));
            return ResponseEntity.ok(RegisterResponse.builder()
                    .message("Validation error")
                    .build());
        }

        Optional<User> user1 = userService.getUserByUsername(request.getUsername());
        Optional<User> user2 = userService.getUserByEmail(request.getEmail());
        if (user1.isPresent()) {
            return ResponseEntity.ok(RegisterResponse.builder()
                    .message("Username has been used")
                    .build());
        } else if (user2.isPresent()) {
            return ResponseEntity.ok(RegisterResponse.builder()
                    .message("Email has been used")
                    .build());
        }

        Permission permission = userService.getPermission("user");
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(request.getPassword());
        User user = User.builder()
                .username(request.getUsername())
                .password(password)
                .email(request.getEmail())
                .permission(permission)
                .build();
        userService.createNewUser(user);
        System.out.println(user.toString());
        System.out.println(permission.toString());

        return ResponseEntity.ok(RegisterResponse.builder()
                .message("register success")
                .build());
    }

}
