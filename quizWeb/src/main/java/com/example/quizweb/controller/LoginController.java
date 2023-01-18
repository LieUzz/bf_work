package com.example.quizweb.controller;

import com.example.quizweb.dao.ChoiceDao;
import com.example.quizweb.domain.Feedback;
import com.example.quizweb.domain.QuestionHibernate;
import com.example.quizweb.domain.User;
import com.example.quizweb.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {
    private final LoginService loginService;

    private final QuizService quizService;


    public LoginController(LoginService loginService,
                           UserService userService,
                           FeedbackService feedbackService,
                           QuizService quizService,
                           QuestionService questionService,
                           QuizQuestionService quizQuestionService,
                           ChoiceDao choiceHibernateDao) {
        this.loginService = loginService;
        this.quizService = quizService;
    }

    @GetMapping("/login")
    public String getLogin(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);

//        userService.getAllUserHibernate();
//        feedbackService.getAllFeedBackHibernate();
//        quizService.getAllQuizHibernate();
//        questionService.getAllQuestionHibernate();
//        quizQuestionService.getAllQuizQuestionHibernate();
        quizService.getChoiceByIdHibernate(1);

//         redirect to /quiz if user is already logged in
        if (session != null && session.getAttribute("user")!=null) {
            User user = (User) session.getAttribute("user");
            if (user.is_admin())
                return "redirect:/admin";
            else
                return "redirect:/home";
        }

        return "login";
    }

    // validate that we are always getting a new session after login
    @PostMapping("/login")
    public String postLogin(@RequestParam String username,
                            @RequestParam String password,
                            HttpServletRequest request,
                            Model model) {

        Optional<User> possibleUser = loginService.validateLogin(username, password);

        if(possibleUser.isPresent()) {
            HttpSession oldSession = request.getSession(false);
            // invalidate old session if it exists
            if (oldSession != null) oldSession.invalidate();

            // generate new session
            HttpSession newSession = request.getSession(true);
            User user = possibleUser.get();
            // store user details in session
            newSession.setAttribute("user", user);

            if (user.is_admin())
                return "redirect:/admin";
            else
                return "redirect:/home";
        } else { // if user details are invalid
            request.setAttribute("loginError", "Username or password is incorrect!");
//            request.getRequestDispatcher("userlogin.html").forward(request, response);
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession oldSession = request.getSession(false);
        // invalidate old session if it exists
        if(oldSession != null) oldSession.invalidate();
        return "login";
    }

    @GetMapping("/register")
    public String getRegister(HttpServletRequest request, Model model) {
        return "register";
    }



    @PostMapping("/register")
    public String doRegister(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String userRePassword,
                             @RequestParam String firstname,
                             @RequestParam String lastname,
                             @RequestParam String address,
                             @RequestParam String phone,
                             @RequestParam String email,
                             @RequestParam Integer isAdmin,
                             HttpServletRequest request) {
        boolean admin = isAdmin == 1;
        User user = new User(0, username, firstname, lastname, address, email, password, phone, admin);
        Optional<User> possibleUser = loginService.validateNewUser(username);

        if (possibleUser.isPresent() || !password.equals(userRePassword)) {
            if (possibleUser.isPresent()) {
                request.setAttribute("registerError", "Username has been used!");
            } else if (!password.equals(userRePassword)){
                request.setAttribute("registerError", "The two passwords you typed do not match!");
            } else {
                request.setAttribute("registerError", "Please complete all required input!");
            }
            return "register";
        } else {
            loginService.registerNewUser(user);
            return "redirect:/login";
        }

    }
}

