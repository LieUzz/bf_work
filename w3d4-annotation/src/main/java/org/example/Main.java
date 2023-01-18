package org.example;

import org.example.config.BeanConfig;
import org.example.service.Player;
import org.example.service.impl.Football;
import org.example.service.impl.Golf;
import org.example.service.impl.Tennis;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        // using constructor to inject tennis coach.
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        Tennis tennis = context.getBean("tennis", Tennis.class);
        Player player1 = new Player(tennis);
        System.out.println(player1.coach.getDailyWorkOutSchedule());


        // using setter to inject golf coach.
        Golf golf = context.getBean("golf", Golf.class);
        Player player2 = new Player();
        player2.setCoach(golf);
        System.out.println(player2.coach.getDailyWorkOutSchedule());

        // using filed to inject football coach.
        // way1:
        System.out.println(context.getBean("football", Football.class).getDailyWorkOutSchedule());
        //  way2:
//        Football football = context.getBean("football", Football.class);
//        Player player3 = new Player();
//        player3.coach = football;
//        System.out.println(player3.coach.getDailyWorkOutSchedule());

    }
}