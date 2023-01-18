package com.example.quizweb.controller;

import com.example.quizweb.domain.*;
import com.example.quizweb.service.QuestionService;
import com.example.quizweb.service.QuizQuestionService;
import com.example.quizweb.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class QuizController {
    private final QuestionService questionService;
    private final QuizService quizService;
    private final QuizQuestionService quizQuestionService;

    private Integer QuestionNumber;

    public QuizController(QuestionService questionService, QuizService quizService, QuizQuestionService quizQuestionService) {
        this.questionService = questionService;
        this.quizService = quizService;
        this.quizQuestionService = quizQuestionService;
    }

    @GetMapping("/quiz")
    public String getQuiz(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if (this.QuestionNumber == null)
            this.QuestionNumber = 0;
        Category category = (Category) session.getAttribute("category");
        User user = (User) session.getAttribute("user");



        System.out.println(category.toString());
        System.out.println(user.toString());
        // random five questions from certain category.
        List<Question> questions = questionService.getFiveQuestionByCategoryId(category);
        session.setAttribute("questions", questions);


        model.addAttribute("question_number", this.QuestionNumber + 1);
        model.addAttribute("question", questions.get(this.QuestionNumber));


        Quiz quiz = quizService.getQuizByUserLastQuiz(quizService.createNewQuiz(user, category));
        session.setAttribute("quiz", quiz);

        if (quiz.getId() != null) {
            System.out.println(quiz.toString());
            List<QuizQuestion> quizQuestionList = quizQuestionService.createQuizQuestionObject(questions, quiz);
            session.setAttribute("quiz_question", quizQuestionList);

            model.addAttribute("quiz", quiz);
            model.addAttribute("quiz_question", quizQuestionList);

        } else {
            System.out.println("no quiz");
        }

        return "quiz";
    }

//    @GetMapping("/quiz/{qNumber}")
//    public String getQuizByQNumber(@PathVariable int qNumber,
//                                 HttpServletRequest request,
//                                 Model model) {
//        this.QuestionNumber = qNumber;
//        System.out.println("This is qNumber " + qNumber);
//        HttpSession session = request.getSession(false);
//        System.out.println(session.getAttribute("user").toString());
//        System.out.println(session.getAttribute(("category").toString()));
//
//        List<Question> questions = (List<Question>) session.getAttribute("questions");
//        Quiz quiz = (Quiz) session.getAttribute("quiz");
//        List<QuizQuestion> quizQuestionList = (List<QuizQuestion>) session.getAttribute("quiz_question");
//
//        for (QuizQuestion q : quizQuestionList) {
//            System.out.println(q.toString());
//        }
//
//
//        model.addAttribute("question_number", this.QuestionNumber+1);
//        model.addAttribute("quiz", quiz);
//        model.addAttribute("quiz_question", quizQuestionList);
//        return "quiz";
//
//    }

    @PostMapping("/quiz")
    public String postQuiz(@RequestParam(required = false) Integer choiceId,
                            @RequestParam String submit,
                            HttpServletRequest request,
                            Model model) {
        HttpSession session = request.getSession(false);
        List<QuizQuestion> quizQuestionList = (List<QuizQuestion>) session.getAttribute("quiz_question");
        List<Question> questions = (List<Question>) session.getAttribute("questions");
        Quiz quiz = (Quiz) session.getAttribute("quiz");

        if (choiceId != null) {
            Choice choice = questionService.getChoiceById(choiceId);
            System.out.println("This is user's choiceId " + choiceId + " " + choice.getQuestion_id());
            for (QuizQuestion q : quizQuestionList) {
                if (q.getQuestion_id() == choice.getQuestion_id())
                    q.setUser_choice_id(choiceId);
            }
        }

        if (submit != null) {
            if (submit.equals("Next")) {
                this.QuestionNumber++;
            } else if (submit.equals("Prev")) {
                this.QuestionNumber--;
            } else if (submit.equals("Submit")) {

                //            Optional check = quizQuestionList.stream().filter(a -> a.getUser_choice_id() == null).findAny();
                //            if (check.isPresent()) {
                //                  request.setAttribute("submit", "There are questions not finished!"); }
                session.removeAttribute("category");
                quizQuestionService.createQuizQuestion(quizQuestionList);
                quizService.setEndTime(quiz);
                return "redirect:/quiz-result";
            } else {
                this.QuestionNumber = Integer.valueOf(submit);
                this.QuestionNumber--;
            }
        }
        model.addAttribute("question_number", this.QuestionNumber+1);
        model.addAttribute("question", questions.get(this.QuestionNumber));
        model.addAttribute("quiz", quiz);
        model.addAttribute("quiz_question", quizQuestionList);

        return "quiz";
    }


    @GetMapping("/quiz-result")
    public String getQuizResult(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        System.out.println(user.toString());
        Optional<Quiz> quizOptional = quizService.getQuizByQuizId((Quiz) session.getAttribute("quiz"));
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            System.out.println(quiz.toString());
            int userCorrect = 0;

            List<QuizQuestion> quizQuestionList = quizQuestionService.getAllQuizQuestion(quiz);
            for (QuizQuestion q : quizQuestionList) {
                System.out.println(q.toString());
            }
            for (QuizQuestion q : quizQuestionList) {
                Choice choice = questionService.getChoiceById(q.getUser_choice_id());
                Question question = questionService.getQuestionById(q.getQuestion_id());
                q.setChoice(choice);
                q.setQuestion(question);
                System.out.println(q.toString());
                if (choice.is_correct()) {
                    userCorrect++;
                    q.setIs_correct("Correct");
                } else {
                    q.setIs_correct("Wrong");
                }
            }

            List<Question> questions = quizQuestionService.getQuestionsByQuiz(quizQuestionList);

            System.out.println("*****");



            for (Question q : questions) {
                System.out.println(q.toString());
            }

            model.addAttribute("user", user);
            model.addAttribute("quiz", quiz);
            model.addAttribute("quizQuestionList", quizQuestionList);
            model.addAttribute("questions", questions);
            model.addAttribute("score", userCorrect);
            String passornot;
            if (userCorrect >= 3)
                passornot = "Pass";
            else
                passornot = "Fail";
            model.addAttribute("passornot", passornot);


        }

        return "quiz_result";
    }

    @PostMapping("/quiz-result")
    public String postQuizResult(HttpServletRequest request, Model model) {


        return "redirect:/home";
    }

}
