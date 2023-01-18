package org.example;

public class Player {
    Coach coach;

    public Player() {}
    public Player(Coach coach) {
        this.coach = coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }
}
