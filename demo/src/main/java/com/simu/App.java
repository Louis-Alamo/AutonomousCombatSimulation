package com.simu;

import com.simu.abstracts.Application;
import com.simu.abstracts.Screen;
import com.simu.controller.GameController;
import com.simu.definition.CreatureFileReader;
import com.simu.definition.MoveFileReader;
import com.simu.tui.ApplicationTUI;

import com.simu.tui.ScreenType;
import com.simu.tui.screen.CreatureSelectionScreen;
import com.simu.tui.screen.DumbScreen;
import com.simu.tui.screen.MenuScreen;
import com.simu.tui.screen.ScreenManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class App {
    public static void main(String[] args) throws IOException {


        //Create dependencies

        CreatureFileReader creatureFileReader = new CreatureFileReader();
        MoveFileReader moveFileReader = new MoveFileReader();

        GameController gameController = new GameController(creatureFileReader, moveFileReader);


        //Make TUI interface
        ScreenManager screenManager = new ScreenManager();
        screenManager.register(ScreenType.DUMB_SCREEN, new DumbScreen());
        screenManager.register(ScreenType.MENU, new MenuScreen(screenManager));
        screenManager.register(ScreenType.CREATURE_SELECTION_SCREEN, new CreatureSelectionScreen(screenManager, gameController));

        //DEfinimos la pantalla principal

        screenManager.navigateTo(ScreenType.CREATURE_SELECTION_SCREEN);


       Application app = new ApplicationTUI(screenManager);

        try {

            app.execute();

        } catch (Exception e) {

        }
  

        //System.out.println(gameController.getCreaturesDefinition());
        //System.out.println(gameController.getMovesDefinition());




    }
}
