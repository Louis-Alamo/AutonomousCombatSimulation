package com.simu.tui.screen;

import com.simu.abstracts.Screen;
import com.simu.tui.ScreenType;

import java.util.HashMap;
import java.util.Map;

public class ScreenManager implements ScreenNavigator, ScreenProvider{

    private Screen currentScreen;
    private final Map<ScreenType, Screen> screenMap;

    public ScreenManager () {
        this.screenMap = new HashMap<>();
        //this.navigateTo(ScreenType.MENU);
    }


    @Override
    public void navigateTo(ScreenType screenType) {

        this.currentScreen = this.screenMap.getOrDefault(screenType, new DumbScreen());
        System.out.println("Se selecciono la interfaz");

//        switch (screenType)  {
//
//            case MENU -> this.currentScreen = new MenuScreen();
//
//            case CREATURE_SELECTION_SCREEN -> this.currentScreen = new CreatureSelectionScreen();
//
//            default -> this.currentScreen = new DumbScreen();
//
//        }

    }

    @Override
    public Screen currentScreen() {

        return this.currentScreen;
    }


    public void register (ScreenType screenType, Screen screen) {

        if (screenType == null && screen == null) {
            throw new IllegalArgumentException("Cannot register a null screen...");
        }

        this.screenMap.put(screenType, screen);
    }

}
