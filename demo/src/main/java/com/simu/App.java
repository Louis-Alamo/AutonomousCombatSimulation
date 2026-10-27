package com.simu;

import com.simu.abstracts.Application;
import com.simu.controller.GameController;
import com.simu.definition.CreatureFileReader;
import com.simu.definition.MoveFileReader;
import com.simu.tui.ApplicationTUI;
import com.simu.tui.ScreenType;
import com.simu.tui.screen.DumbScreen;
import com.simu.tui.screen.MenuScreen;
import com.simu.tui.screen.ScreenManager;

import java.io.IOException;


public class App {
    public static void main(String[] args) throws IOException {


        //Create dependencies

        CreatureFileReader creatureFileReader = new CreatureFileReader();
        MoveFileReader moveFileReader = new MoveFileReader();

        GameController gameController = new GameController(creatureFileReader, moveFileReader);


        //Make TUI interface
        ScreenManager screenManager = new ScreenManager();

        Application app = new ApplicationTUI(screenManager);


        System.out.println(gameController.getCreaturesDefinition());
        System.out.println(gameController.getMovesDefinition());




    }
}
