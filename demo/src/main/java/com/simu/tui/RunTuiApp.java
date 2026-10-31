package com.simu.tui;

import com.simu.abstracts.Application;
import com.simu.tui.view.screen.DumbScreen;
import com.simu.tui.view.screen.MenuScreen;
import com.simu.tui.view.screen.ScreenManager;

public class RunTuiApp {

    public static void main(String[] args)   {
        //MenuScreen menu = new MenuScreen();
        DumbScreen dumbScreen = new DumbScreen();

        try {
            ScreenManager screenManager = new ScreenManager();
            Application app = new ApplicationTUI(screenManager);
            app.execute();

        } catch (Exception e) {

            System.out.println(e);

        }

    }
}
