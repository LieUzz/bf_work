package org.example.service.impl;

import org.example.service.Coach;
import org.springframework.stereotype.Component;

@Component("football")
public class Football implements Coach {
    @Override
    public String getDailyWorkOutSchedule() {
        return "This is football's schedule";
    }
}
