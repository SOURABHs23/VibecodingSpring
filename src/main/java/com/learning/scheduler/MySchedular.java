package com.learning.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MySchedular {

    @Scheduled(fixedRate = 5000)
    public void cleanFiles(){
        System.out.println("Cleaning files at " + System.currentTimeMillis()/1000);
    }
}
