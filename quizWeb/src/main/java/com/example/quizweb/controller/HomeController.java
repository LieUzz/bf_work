package com.example.quizweb.controller;

import com.example.quizweb.dao.CategoryDao;
import com.example.quizweb.domain.Category;
import com.example.quizweb.domain.Feedback;
import com.example.quizweb.domain.Quiz;
import com.example.quizweb.domain.User;
import com.example.quizweb.service.CategoryService;
import com.example.quizweb.service.FeedbackService;
import com.example.quizweb.service.LoginService;
import com.example.quizweb.service.QuizService;
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
public class HomeController {

    private final CategoryService categoryService;
    private final QuizService quizService;

    private final FeedbackService feedbackService;

    public HomeController(CategoryService categoryService, QuizService quizService, FeedbackService feedbackService) {
        this.categoryService = categoryService;
        this.quizService = quizService;
        this.feedbackService = feedbackService;
    }

    @GetMapping("/home")
    public String getLogin(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
//        if (session != null && session.getAttribute("category") != null) {
//            return "redirect:/quiz";
//        }

        List<Category> categories = categoryService.getAllCategories();
        for (Category cat:categories) {
            System.out.println(cat.getId() + cat.getCategory_name());
        }
        assert session != null;
        User user = (User) session.getAttribute("user");
        List<Quiz> quizList = quizService.getAllQuizByUser(user);
        model.addAttribute("quizList", quizList);
        model.addAttribute("categories", categories);
        return "home";
    }

    @PostMapping("/home")
    public String postHome(@RequestParam(required = false) Integer categoryId,
                           @RequestParam(required = false) String submit,
                            HttpServletRequest request,
                            Model model) {
        HttpSession session = request.getSession(false);
        if (submit.equals("submit")) {

            Optional<Category> possibleCategory = categoryService.validateCategoryById(categoryId);

            if (possibleCategory.isPresent()) {
                session.setAttribute("category", possibleCategory.get());
                return "redirect:/quiz";
            }
        } else {
            Quiz tmp = new Quiz();
            tmp.setId(Integer.valueOf(submit));
            Optional<Quiz> quizOptional = quizService.getQuizByQuizId(tmp);
            if (quizOptional.isPresent()) {
                session.setAttribute("quiz", quizOptional.get());
                return "redirect:/quiz-result";
            }
        }

        return "home";

    }

    @GetMapping("/feedback")
    public String getFeedback(HttpServletRequest request, Model model) {
        return "feedback";
    }

    @PostMapping("/feedback")
    public String postFeedback(@RequestParam(required = false) Integer star,
                               @RequestParam(required = false) String feedback,
                               HttpServletRequest request,
                               Model model) {

        feedbackService.createNewFeedback(new Feedback(star, feedback));

        return "redirect:/home";
    }

    @GetMapping("/contact")
    public String getContact(HttpServletRequest request, Model model) {
        return "contact";
    }

    @PostMapping("/contact")
    public String postContact(HttpServletRequest request, Model model) {
        return "redirect:/home";
    }
}
