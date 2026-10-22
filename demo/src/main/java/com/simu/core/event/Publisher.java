package com.simu.core.event;

public interface Publisher {

    void subscribe(Observer subscriber);

    void unsubscribe(Observer subscriber);

}
