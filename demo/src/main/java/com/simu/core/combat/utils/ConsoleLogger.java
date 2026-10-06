package com.simu.core.combat.utils;

import java.util.ArrayList;

public class ConsoleLogger {

    private ArrayList<String> logs = new ArrayList<>();

    private static ConsoleLogger instance;

    private ConsoleLogger() {
    }

    public static ConsoleLogger getInstance() {
        if (instance == null) {
            instance = new ConsoleLogger();
        }

        return instance;
    }

    public void infolog(String text) {
        System.out.println("-- [INFO]: " + text);
    }

    public void battleLog(String text) {
        System.out.println("-- [BATTLE]: " + text);
    }

    public void errorLog (String text) {
        System.out.println("-- [ERROR]: " + text);
    }

    public void warnLog(String text) {
        System.out.println("-- [WARN]: " + text);
    }

    public void debugLog(String text) {
        System.out.println("-- [DEBUG]: " + text);
    }
}
