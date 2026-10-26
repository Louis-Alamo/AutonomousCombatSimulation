package com.simu.core.event;

public interface CommandPublisher extends Publisher {

    void emit(Event event);
}
