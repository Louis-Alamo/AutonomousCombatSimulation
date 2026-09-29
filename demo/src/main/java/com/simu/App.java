package com.simu;

import com.simu.battleengine.BattleManagerImpl;
import com.simu.factory.CreatureFactory;
import com.simu.models.creatures.Charmander;
import com.simu.models.creatures.Creature;
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
        
        battleManager.startBattle(
                CreatureFactory.createCreature("pikachu"),
                CreatureFactory.createCreature("charmander")
        );

        logger.infolog("Se termino la ejecucion...");

    }
}
