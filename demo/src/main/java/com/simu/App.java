package com.simu;

import com.simu.abstracts.Application;
import com.simu.controller.GameController;
import com.simu.definition.CreatureFileReader;
import com.simu.definition.MoveFileReader;
import com.simu.tui.ApplicationTUI;

import com.simu.tui.enums.ScreenType;
import com.simu.tui.screen.*;

import java.io.IOException;


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
        screenManager.register(ScreenType.MOVE_SELECTION_SCREEN, new MoveSelectionScreen(screenManager));

        //DEfinimos la pantalla principal

        screenManager.navigateTo(ScreenType.MOVE_SELECTION_SCREEN);


       Application app = new ApplicationTUI(screenManager);

        try {

            app.execute();

        } catch (Exception e) {

        }
  

        //System.out.println(gameController.getCreaturesDefinition());
        //System.out.println(gameController.getMovesDefinition());




    }
}
