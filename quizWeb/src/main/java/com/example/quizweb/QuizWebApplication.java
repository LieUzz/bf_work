package com.example.quizweb;

import com.example.quizweb.config.JdbcConfig;
import com.example.quizweb.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@SpringBootApplication
@ServletComponentScan
public class QuizWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizWebApplication.class, args);

//        ApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
//        UserService userService = context.getBean("userService", UserService.class);
//
//        userService.getEmployeeByIdHibernate(1);

    }

}
