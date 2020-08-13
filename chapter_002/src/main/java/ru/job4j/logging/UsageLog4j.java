package ru.job4j.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        byte code = 25;
        char c = 'A';
        boolean check = true;
        long ageInDays = 12045;
        float height = 175.5f;
        double anything = 123456.345d;

        LOG.debug("User info name : {}, age : {}", name, age);
        LOG.debug("code : {}, c : {}", code, c);
        LOG.debug("check : {}, ageInDays : {}", check, ageInDays);
        LOG.debug("height : {}, anything : {}", height, anything);
    }

//    public static void main(String[] args) {
//        LOG.trace("trace message");
//        LOG.debug("debug message");
//        LOG.info("info message");
//        LOG.warn("warn message");
//        LOG.error("error message");
//    }

}
