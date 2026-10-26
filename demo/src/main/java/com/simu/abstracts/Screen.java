package com.simu.abstracts;

import dev.tamboui.terminal.Frame;
import dev.tamboui.tui.event.Event;


public interface Screen {


    void show(Frame frame);

    boolean handleEvents (Event event);
}
