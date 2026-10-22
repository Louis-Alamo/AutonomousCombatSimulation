package com.simu.core.logger;

import com.simu.core.enums.LogType;


public class ConsoleLogger implements AbstractLogger {

    private final String WARNING = "-- [WARNING]  ";
    private final String DEBUG = "-- [DEBUG]  ";
    private final String INFO = "-- [INFO]  ";
    private final String ERROR = "-- [ERROR]  ";
    private final String BATTLE = "-- [BATTLE]  ";



    private String selectTypeLog(LogType logType) {

        return switch (logType)  {
            case WARNING -> this.WARNING;
            case DEBUG -> this.DEBUG;
            case INFO -> this.INFO;
            case ERROR -> this.ERROR;
            case BATTLE -> this.BATTLE;
            default -> "";
        };

    }

    @Override
    public void log(LogType logType, String message) {

        System.out.println(
                logType.getColor() + this.selectTypeLog(logType) + message + LogType.ANSI_RESET.getColor() + " "
        );
    }
}
