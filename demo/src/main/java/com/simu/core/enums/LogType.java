package com.simu.core.enums;

public enum LogType {
    WARNING("\u001B[93m"),
    BATTLE("\u001B[95m"),
    INFO("\u001B[94m"),
    DEBUG("\u001B[90m"),
    ERROR("\u001B[91m");

    private String color;

    private LogType (String color) {
        this.color = color;
    }
}
