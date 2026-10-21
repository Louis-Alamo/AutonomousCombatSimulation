package com.simu.core.logger;

import com.simu.core.dao.EventLogger;
import com.simu.core.enums.LogType;
import com.simu.core.event.Event;

public interface AbstractLogger {

    void onEvent(Event event);

    void onEvent(EventLogger event);

}
