package com.simu.tui.view;

import dev.tamboui.terminal.Frame;
import dev.tamboui.tui.event.Event;


public interface Screen {

    boolean handleEvents (Event event);

    void show(Frame frame);


}
