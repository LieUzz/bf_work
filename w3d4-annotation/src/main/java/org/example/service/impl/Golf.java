package org.example.service.impl;

import org.example.service.Coach;
import org.springframework.stereotype.Component;

@Component("golf")
public class Golf implements Coach {
    @Override
    public String getDailyWorkOutSchedule() {
        return "This is Golf's schedule";
    }
}
