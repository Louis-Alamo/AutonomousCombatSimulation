package com.simu.core.logger;

import com.simu.core.enums.LogType;


public class ConsoleLogger implements AbstractLogger {


    @Override
    public void log(LogType logType, String message) {

        System.out.println(
                logType.getColor()
                + message
                + LogType.ANSI_RESET
        );
    }
}
