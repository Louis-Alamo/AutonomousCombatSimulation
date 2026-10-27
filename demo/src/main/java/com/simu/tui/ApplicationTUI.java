package com.simu.tui;

import com.simu.abstracts.Application;
import com.simu.tui.screen.DumbScreen;
import com.simu.tui.screen.MenuScreen;
import dev.tamboui.terminal.Frame;
import dev.tamboui.tui.TuiRunner;
import dev.tamboui.tui.event.Event;
import dev.tamboui.tui.event.KeyEvent;

public class ApplicationTUI implements Application {

    private final ScreenType screenType;
    private final MenuScreen menuScreen;
    private final DumbScreen dumbScreen;
    public ApplicationTUI(ScreenType screenType, MenuScreen menuScreen, DumbScreen dumbScreen) {
        this.screenType = screenType;
        this.menuScreen = menuScreen;
        this.dumbScreen = dumbScreen;
    }

    public boolean handleEvent(Event event) {

        return switch (this.screenType) {
            case MENU -> this.menuScreen.handleEvents(event);
            case DUMB_SCREEN -> this.dumbScreen.handleEvents(event);
        };

    }



    public void renderUI(Frame frame) {

        switch (this.screenType) {
            case MENU -> this.menuScreen.show(frame);
            case DUMB_SCREEN -> this.dumbScreen.show(frame);
        }

    }


    @Override
    public void execute() throws Exception {
        try (var tui = TuiRunner.create()) {
            tui.run(
                    (event, runner) -> {
                        if (event instanceof KeyEvent key && key.isQuit()) {
                            runner.quit();
                            return false;
                        }
                        return handleEvent(event);
                    },
                    this::renderUI
            );
        }
    }
}
