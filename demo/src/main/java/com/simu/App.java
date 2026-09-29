package com.simu;

import com.simu.battleengine.BattleManagerImpl;
import com.simu.models.creatures.Charmander;
import com.simu.models.creatures.Pikachu;
import com.simu.utils.ConsoleLogger;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        ConsoleLogger logger = ConsoleLogger.getInstance();
        logger.infolog("Se inicio la ejecucion");

        BattleManagerImpl battleManager = new BattleManagerImpl(logger);

        Pikachu pikachu = new Pikachu(logger);
        Charmander charmander = new Charmander(logger);

        battleManager.startBattle(pikachu, charmander);

        logger.infolog("Se termino la ejecucion...");

    }
}
