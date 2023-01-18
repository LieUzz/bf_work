package com.example.quizweb.service;

import com.example.quizweb.dao.*;
import com.example.quizweb.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizService {
    private final QuizDao quizJdbcDao;

    private final QuizDao quizHibernateDao;
    private final QuizQuestionDao quizQuestionHibernateDao;
    private final QuestionDao questionHibernateDao;

    private final ChoiceDao choiceHibernateDao;
    private final UserDao userHibernateDao;
    private final CategoryDao categoryHibernateDao;
    private final ChoiceDao choiceJdbcDao;

    @Autowired
    public QuizService(QuizDao quizJdbcDao,
                       QuizDao quizHibernateDao,
                       QuizQuestionDao quizQuestionHibernateDao,
                       QuestionDao questionHibernateDao,
                       ChoiceDao choiceHibernateDao,
                       UserDao userHibernateDao,
                       CategoryDao categoryHibernateDao,
                       ChoiceDao choiceJdbcDao) {
        this.quizJdbcDao = quizJdbcDao;
        this.quizHibernateDao = quizHibernateDao;
        this.quizQuestionHibernateDao = quizQuestionHibernateDao;
        this.questionHibernateDao = questionHibernateDao;
        this.choiceHibernateDao = choiceHibernateDao;
        this.userHibernateDao = userHibernateDao;
        this.categoryHibernateDao = categoryHibernateDao;
        this.choiceJdbcDao = choiceJdbcDao;
    }

    public void createNewQuiz(Quiz quiz) {
        quizJdbcDao.createNewQuiz(quiz);
    }

    public Quiz createNewQuiz(User user, Category category) {
        Quiz quiz = new Quiz();
        quiz.setUser_id(user.getId());
        quiz.setCat_id(category.getId());
        quiz.setQuiz_name(category.getCategory_name());
        long time = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(time);
        quiz.setStart_time(timestamp);
        createNewQuiz(quiz);
        return quiz;
    }

    public List<Quiz> getAllQuiz() {
        return quizJdbcDao.getAllQuiz();
    }

    public Quiz getQuizByUserLastQuiz(Quiz quiz) {
        List<Quiz> quizList=  quizJdbcDao.getAllQuiz().stream()
                .filter(a -> a.getUser_id() == quiz.getUser_id())
                .collect(Collectors.toList());
        if (quizList.size() > 0) {
            return quizList.get(quizList.size()-1);
        } else {
            return new Quiz();
        }
    }

    public void setEndTime(Quiz quiz) {
        long time = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(time);
        quiz.setEnd_time(timestamp);
        quizJdbcDao.setEndTimeByQuizId(quiz);
    }

    public List<Quiz> getAllQuizByUser(User user) {
        return quizJdbcDao.getAllQuizByUser(user);
    }

    public Optional<Quiz> getQuizByQuizId(Quiz quiz) {
        return quizJdbcDao.getAllQuiz().stream()
                .filter(a -> a.getId() == quiz.getId())
                .findFirst();
    }


    @Transactional
    public List<Quiz> getAllQuizHibernate() {
        List<Quiz> quizList = quizHibernateDao.getAllQuiz();

        for (Quiz u : quizList)
            System.out.println(u.toString());
        return quizList;
    }

    @Transactional
    public List<QuizResult> getAllQuizResult() {
        List<Quiz> quizList = quizHibernateDao.getAllQuiz();

        List<QuizResult> result = new ArrayList<>();
        for (Quiz q : quizList) {
            List<QuizQuestionHibernate> quizQuestions = quizQuestionHibernateDao.getAllQuizQuestionsHibernate(q);
            User user = userHibernateDao.getUserById(q.getUser_id());
            Category category = categoryHibernateDao.getCategoryById(q.getCat_id());
//            List<String> user_choice_id = quizQuestions.
//                    stream().
//                    map(a->choiceHibernateDao.getChoiceById(a.getUser_choice_id()).getDescription()).
//                    collect(Collectors.toList());
//            System.out.println("get user success" + user_choice_id.size());
            Integer score = Math.toIntExact(quizQuestions
                    .stream()
                    .filter(a -> choiceHibernateDao.getChoiceById(a.getUser_choice_id()).is_correct())
                    .count());
            System.out.println("get score success" + score);
            List<Question> questions = quizQuestions
                    .stream()
                    .map(a -> questionHibernateDao.getQuestionById(a.getQuestion_id()))
                    .collect(Collectors.toList());

            for (int i = 0; i < questions.size(); i++) {
                questions.get(i).setUserChoice(choiceHibernateDao.getChoiceById(quizQuestions.get(i).getUser_choice_id()).getDescription());
            }

            System.out.println("get questions success");
            questions.forEach(System.out::println);
//            List<String> correct_choice_id = questions
//                    .stream()
//                    .filter(a->a.getCorrectChoiceId()!=-1)
//                    .map(a->choiceHibernateDao.getChoiceById(a.getCorrectChoiceId()).getDescription())
//                    .collect(Collectors.toList());
//            System.out.println("get correct_choice_id success");
//            correct_choice_id.forEach(System.out::println);
            for (Question question: questions) {
                question.setChoices(choiceHibernateDao.getAllChoiceByQuestionId(
                        new QuestionHibernate(question.getId(), -1, "")));
                for (Choice c:question.getChoices()){
                    question.setCorrectChoiceId(c.getId());
                    question.setCorrectChoice(c.getDescription());
                }
            }
            result.add(new QuizResult(q.getId(),
                    user,
                    category,
                    q.getQuiz_name(),
                    q.getStart_time(),
                    q.getEnd_time(),
                    questions,
                    score));
        }
        Collections.reverse(result);
        return result;
    }
    @Transactional
    public List<QuizResult> getAllQuizResultByUser(User user) {
        return getAllQuizResult().stream().filter(a->a.getUser().getId() == user.getId()).collect(Collectors.toList());
    }

    @Transactional
    public List<QuizResult> getAllQuizResultByCategory(Category category) {
        return getAllQuizResult().stream().filter(a->a.getCategory().getId() == category.getId()).collect(Collectors.toList());
    }

    public List<User> filterByUser(List<QuizResult> quizList) {
        return quizList.stream().map(QuizResult::getUser).distinct().collect(Collectors.toList());
    }

    public List<Category> filterByCategory(List<QuizResult> quizList ) {
        return quizList.stream().map(QuizResult::getCategory).distinct().collect(Collectors.toList());
    }

    @Transactional
    public void getChoiceByIdHibernate(int id) {
        Choice choice = choiceHibernateDao.getChoiceById(id);
        System.out.println("====== "+choice.toString());
    }

    @Transactional
    public QuizResult filterByQuizId(Integer id) {
        return getAllQuizResult().stream().filter(a-> Objects.equals(a.getId(), id)).findFirst().get();
    }
}
