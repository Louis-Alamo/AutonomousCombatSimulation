package com.simu.tui;

import com.simu.abstracts.Application;
import com.simu.tui.screen.DumbScreen;
import com.simu.tui.screen.MenuScreen;

public class RunTuiApp {

    public static void main(String[] args)   {
        MenuScreen menu = new MenuScreen();
        DumbScreen dumbScreen = new DumbScreen();

        try {

            Application app = new ApplicationTUI(ScreenType.MENU, menu, dumbScreen);
            app.execute();

        } catch (Exception e) {

            System.out.println(e);

        }

    }
}
