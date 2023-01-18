package org.example.service.impl;

import org.example.service.Coach;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

@Component("tennis")
public class Tennis implements Coach {
    private String[] schedules;

    @PostConstruct
    public void loadSchedules() {
        try {
            this.schedules = Files.readAllLines(Paths.get("src/main/java/org/example/Tennis_Schedules.txt")).toArray(new String[0]);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDailyWorkOutSchedule() {
        if (schedules!=null) {
            Random random = new Random();
            return schedules[random.nextInt(schedules.length)];
        }
        return "This is tennis's schedule";
    }
}
