package org.example.service;

import org.example.service.Coach;
import org.example.service.impl.Tennis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Player {

    public Coach coach;

    public Player() {}
    @Autowired
    public Player(Tennis coach) {
        this.coach = coach;
    }
    @Autowired
    @Qualifier("golf")
    public void setCoach(Coach coach) {
        this.coach = coach;
    }
    
}
