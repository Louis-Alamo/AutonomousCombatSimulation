package com.simu.utils;

import java.util.ArrayList;

public class ConsoleLogger {

    private ArrayList<String> logs = new ArrayList<>();

    private static ConsoleLogger instance;

    private ConsoleLogger() {

    }

    public ConsoleLogger getInstance() {
        if (instance == null) {
            this.instance = new ConsoleLogger();

        }

        return this.instance;
    }

    public void infolog(String text) {
        System.out.println("-- [INFO]:  " + text);
    }

    public void battleLog(String text) {
        System.out.println("-- [BATTLE]: " + text);
    }

}
