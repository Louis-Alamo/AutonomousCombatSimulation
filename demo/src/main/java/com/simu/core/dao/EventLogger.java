package com.simu.core.dao;

import com.simu.core.enums.LogType;

public record EventLogger(
        LogType logType,
        String message
        
) {


}
