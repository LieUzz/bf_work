package org.example;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class Main {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        // using constructor to inject tennis coach.

        Tennis tennis = applicationContext.getBean("tennisCoach", Tennis.class);
        Player player1 = new Player(tennis);
        System.out.println(player1.coach.getDailyWorkOutSchedule());

        // using setter to inject golf coach.
        Golf golf = applicationContext.getBean("golfCoach", Golf.class);
        Player player2 = new Player();
        player2.setCoach(golf);
        System.out.println(player2.coach.getDailyWorkOutSchedule());

        // using setter to inject football coach.
        Football football = applicationContext.getBean("footballCoach", Football.class);
        Player player3 = new Player();
        player3.setCoach(football);
        System.out.println(player3.coach.getDailyWorkOutSchedule());
    }
}