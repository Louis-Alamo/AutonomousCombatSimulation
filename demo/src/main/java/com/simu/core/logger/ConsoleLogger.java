package com.simu.core.logger;

import com.simu.core.enums.LogType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ConsoleLogger implements AbstractLogger {

    private static final String LOG_FILE_PATH = "demo/log/battle.log";
    private final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final String LOG_HEADER = "=== LOG " + LocalDateTime.now().format(this.DATE_TIME_FORMAT) + " === " + System.lineSeparator();

    private final String WARNING = "-- [WARNING]  ";
    private final String DEBUG = "-- [DEBUG]  ";
    private final String INFO = "-- [INFO]  ";
    private final String ERROR = "-- [ERROR]  ";
    private final String BATTLE = "-- [BATTLE]  ";

    private final List<String> logHistory = new ArrayList<>();


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

        String log = this.selectTypeLog(logType) + message;
        this.logHistory.add(log);

        System.out.println(logType.getColor() + log + LogType.ANSI_RESET.getColor() + " " );
    }

    @Override
    public void saveData() {
        try (FileWriter writer = new FileWriter(LOG_FILE_PATH, true)) {

            writer.write(this.LOG_HEADER);

            for (String log: this.logHistory) {
                writer.write(log + System.lineSeparator());
            }

            writer.write(System.lineSeparator());

            this.log(LogType.INFO, "Log data saved successfully.");

        } catch (IOException e) {
            this.log(LogType.ERROR, "Failed to read from or write to the log file.");
        }
    }


}
