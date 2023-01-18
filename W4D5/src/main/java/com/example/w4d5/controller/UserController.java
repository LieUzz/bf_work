package com.example.w4d5.controller;


import com.example.w4d5.domain.*;
import com.example.w4d5.domain.ResponseStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController  // = @Controller + @ResponseBody
//@RequestMapping("users")
public class UserController {

    private final List<User> users;
    private final List<Quiz> quizList;

    public UserController() {
        this.users = new ArrayList<>();
        this.quizList = new ArrayList<>();
        users.add(User.builder().userId(1).username("Jerry123").password("jerry123").firstname("Jerry").lastname("Johnson").activate(true).build());
        users.add(User.builder().userId(2).username("Tom123").password("tom123").firstname("Tom").lastname("Miller").activate(true).build());
        quizList.add(Quiz.builder().userId(1).quizName("math").build());
        quizList.add(Quiz.builder().userId(1).quizName("history").build());
        quizList.add(Quiz.builder().userId(2).quizName("english").build());

    }

    @GetMapping("/user")
    public AllUserResponse getAllUsers() {
        return AllUserResponse.builder()
                .status(
                        ResponseStatus.builder()
                                .success(true)
                                .message("Returning all Users")
                                .build()
                )
                .users(users)
                .build();
    }

    @PostMapping("/user")
    public UserResponse createNewBook(
            @Valid @RequestBody UserRequest user,
            BindingResult bindingResult
    ){
        // perform validation check
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(error -> System.out.println(
                    "ValidationError in " + error.getObjectName() + ": " + error.getDefaultMessage()));
            return UserResponse.builder()
                    .status(
                            ResponseStatus.builder()
                                    .success(false)
                                    .message("Validation error")
                                    .build()
                    )
                    .build();
        }
        // validation passed, create new book
        User newUser = User.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(user.getPassword())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .activate(user.getActivate())
                .build();
        users.add(newUser);

        return UserResponse.builder()
                .status(
                        ResponseStatus.builder()
                                .success(true)
                                .message("New User created")
                                .build()
                )
                .user(newUser)
                .build();
    }

//    @GetMapping("/user")
//    public UserResponse getUserById(@RequestParam(name = "userId", required = false) Integer userId) {
//        Optional<User> userOptional = users.stream()
//                .filter(user -> userId.equals(user.getUserId()))
//                .findFirst();
//
//        if (userOptional.isPresent()) {
//                return UserResponse.builder()
//                        .status(
//                                ResponseStatus.builder()
//                                        .success(true)
//                                        .message("Success! User was found")
//                                        .build()
//                        )
//                        .user(userOptional.get())
//                        .build();
//            } else {
//                return UserResponse.builder()
//                        .status(
//                                ResponseStatus.builder()
//                                        .success(false)
//                                        .message("Oops, user not found")
//                                        .build()
//                        )
//                        .build();
//            }
//
//    }

    @DeleteMapping("/user")
    public UserResponse deleteUser(@RequestParam(name = "userId", required = false) Integer userId) {
        Optional<User> userOptional = users.stream()
                .filter(user -> userId.equals(user.getUserId()))
                .findFirst();

        if (userOptional.isPresent()) {
            Iterator<User> iterator = users.iterator();

            while(iterator.hasNext()) {
                if (userId.equals(iterator.next().getUserId())){
                    iterator.remove();
                }
            }
            for (User user :users)
                System.out.println(user.getUsername());
            return UserResponse.builder()
                    .status(
                            ResponseStatus.builder()
                                    .success(true)
                                    .message("Success! User was delete")
                                    .build()
                    )
                    .user(userOptional.get())
                    .build();
        } else {
            return UserResponse.builder()
                    .status(
                            ResponseStatus.builder()
                                    .success(false)
                                    .message("Oops, user not found")
                                    .build()
                    )
                    .build();
        }


    }

    @PatchMapping ("/user/{userId}/status")
    public UserResponse patchUser(@RequestParam(name = "activate", required = false) Boolean activate,
                                  @PathVariable Integer userId) {
        System.out.println(userId);
        System.out.println(activate);
        Optional<User> userOptional = users.stream()
                .filter(user -> userId.equals(user.getUserId()))
                .findFirst();

        if (userOptional.isPresent()) {
            Iterator<User> iterator = users.iterator();

            while(iterator.hasNext()) {
                if (userId.equals(iterator.next().getUserId())){
                    iterator.next().setActivate(activate);
                    System.out.println(iterator.next().toString());
                }
            }

            return UserResponse.builder()
                    .status(
                            ResponseStatus.builder()
                                    .success(true)
                                    .message("Success! User Status has been updated")
                                    .build()
                    )
                    .user(userOptional.get())
                    .build();
        } else {
            return UserResponse.builder()
                    .status(
                            ResponseStatus.builder()
                                    .success(false)
                                    .message("Oops, user not found")
                                    .build()
                    )
                    .build();
        }
    }

    @GetMapping("/quiz")
    public AllQuizResponse getAllQuiz(@RequestParam(name = "userId", required = false) Integer userId) {

        Optional<User> userOptional = users.stream()
                .filter(user -> userId.equals(user.getUserId()))
                .findFirst();
        if (userOptional.isPresent()) {
            List<Quiz> quizzes = quizList.stream().filter(a->a.getUserId().equals(userId)).collect(Collectors.toList());
            return AllQuizResponse.builder()
                    .status(
                            ResponseStatus.builder()
                                    .success(true)
                                    .message("Returning all quizzes")
                                    .build()
                    )
                    .quizList(quizzes)
                    .build();
        }
        else {
            return AllQuizResponse.builder()
                    .status(
                            ResponseStatus.builder()
                                    .success(true)
                                    .message("User Not Found")
                                    .build()
                    )
                    .build();
        }
    }


}
