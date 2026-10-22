package com.simu.core.logger;

import com.simu.core.enums.LogType;


public interface AbstractLogger {

    void log(LogType logType, String message);


}
