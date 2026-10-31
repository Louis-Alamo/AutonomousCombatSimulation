package com.simu.tui.view;

import dev.tamboui.terminal.Frame;
import dev.tamboui.tui.event.Event;

public interface Panel {
    
    boolean handleEvent(Event event);
    void render(Frame parentFrame);
}
