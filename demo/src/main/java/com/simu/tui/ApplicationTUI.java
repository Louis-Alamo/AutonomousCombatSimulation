package com.simu.tui;

import com.simu.abstracts.Application;
import com.simu.tui.screen.DumbScreen;
import com.simu.tui.screen.MenuScreen;
import com.simu.tui.screen.ScreenManager;
import dev.tamboui.terminal.Frame;
import dev.tamboui.tui.TuiRunner;
import dev.tamboui.tui.event.Event;
import dev.tamboui.tui.event.KeyEvent;

public class ApplicationTUI implements Application {

    private final ScreenManager screenManager;

    public ApplicationTUI(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    public boolean handleEvent(Event event) {

        return this.screenManager.currentScreen().handleEvents(event);

//        return switch (this.screenType) {
//            case MENU -> this.menuScreen.handleEvents(event);
//            case DUMB_SCREEN -> this.dumbScreen.handleEvents(event);
//        };

    }



    public void renderUI(Frame frame) {

        this.screenManager.currentScreen().show(frame);

//        switch (this.screenType) {
//            case MENU -> this.menuScreen.show(frame);
//            case DUMB_SCREEN -> this.dumbScreen.show(frame);
//        }

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
