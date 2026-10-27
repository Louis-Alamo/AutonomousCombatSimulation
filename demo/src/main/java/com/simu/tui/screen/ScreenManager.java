package com.simu.tui.screen;

import com.simu.abstracts.Screen;
import com.simu.tui.ScreenType;

public class ScreenManager implements ScreenNavigator, ScreenProvider{

    private ScreenType screenType;
    private Screen currentScreen;


    @Override
    public void navigateTo(ScreenType screenType) {
        switch (this.screenType)  {

            case MENU -> this.currentScreen = new MenuScreen();

            case CREATURE_SELECTION_SCREEN -> this.currentScreen = new CreatureSelectionScreen();

            default -> this.currentScreen = new DumbScreen();

        }

    }

    @Override
    public Screen currentScreen() {

        return this.currentScreen;
    }

}
