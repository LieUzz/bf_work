package com.example.w3d5;

import com.example.w3d5.dao.PastryDao;
import com.example.w3d5.domain.Pastry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class W3d5Application {

    private static PastryDao pastryDao;

    @Autowired
    public W3d5Application(PastryDao pastryDao) {
        W3d5Application.pastryDao = pastryDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(W3d5Application.class, args);
//        System.out.println("This is the result: " + pastryDao.getPastryCount());

//        pastryDao.createNewPastry("caramel pudding", 6.59f);

        System.out.println(pastryDao.getPastryById(1).toString());

        List<Pastry> pastries = pastryDao.getAllPastry();

        for (Pastry pastry : pastries) {
            System.out.println(pastry.toString());
        }

        pastryDao.updatePastryById(9, "caramel brulee", 6.99f);

        pastryDao.deletePastryById(8);



    }



}
