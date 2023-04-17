package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Gukov Daniil";
        short computers = 2;
        char sign = 'G';
        int age = 31;
        double height = 165.5;
        float weight = 65.40F;
        long bankAccount = 123456789L;
        boolean male = true;
        byte workDays = 5;
        LOG.debug("User info name : {}, age : {}, height : {}, weight: {}, \n bank account : {},"
                        + " male: {}, work days: {}, work place {}, sign: {} ",
                name, age, height, weight, bankAccount, male, workDays, computers, sign);
    }
}