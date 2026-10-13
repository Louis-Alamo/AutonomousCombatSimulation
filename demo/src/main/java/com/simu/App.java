package com.simu;


import com.simu.core.combat.battle.TurnBattleManager;
import com.simu.core.combat.queue.TurnQueue;
import com.simu.core.contracts.BattleManager;
import com.simu.core.contracts.BattleQueue;
import com.simu.core.entity.creature.Creature;
import com.simu.core.enums.CreatureID;
import com.simu.core.factory.CreatureFactory;
import com.simu.core.utils.ConsoleLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        ConsoleLogger logger = ConsoleLogger.getInstance();
        logger.infolog("Execution started.");

        ArrayList<Creature> creatures = new ArrayList<>(
                List.of(
                        Objects.requireNonNull(CreatureFactory.createCreature(CreatureID.SQUIRTLE)),
                        Objects.requireNonNull(CreatureFactory.createCreature(CreatureID.CHARMANDER)),
                        Objects.requireNonNull(CreatureFactory.createCreature(CreatureID.PIKACHU)),
                        Objects.requireNonNull(CreatureFactory.createCreature(CreatureID.BULBASAUR))
                ));


        logger.debugLog("Creatures created successfully");

        creatures.forEach(Creature::creatureDataView);


        BattleQueue battleQueue = new TurnQueue();

        BattleManager battleManager = new TurnBattleManager(battleQueue, logger);
        battleManager.addCombatants(creatures.get(0), creatures.get(1));
        battleManager.startBattle();

        logger.infolog("Execution finished.");

        System.exit(0);

        /*
         *
         * ArrayList<Creature> creatures = new ArrayList<>(
         * List.of(
         * CreatureFactory.createCreature("squirtle"),
         * CreatureFactory.createCreature("charmander"),
         * CreatureFactory.createCreature("pikachu"),
         * CreatureFactory.createCreature("bulbasaur")
         * )
         * );
         *
         * TurnQueue colaBatalla = new TurnQueue(creatures);
         *
         *
         * for (int i = 0; i <= 7; i++) {
         * System.out.println("Turno " + colaBatalla.getCurrentTurn() + " Pokemon: " +
         * colaBatalla.getNextCombatant().getCreatureName());
         * }
         *
         */

        // ConsoleLogger logger = ConsoleLogger.getInstance();
        // logger.infolog("Se inicio la ejecucion");
        //
        // BattleManagerImpl battleManager = new BattleManagerImpl(logger);
        //
        // battleManager.startBattle(
        // CreatureFactory.createCreature("pikachu"),
        // CreatureFactory.createCreature("charmander")
        // );
        //
        // logger.infolog("Se termino la ejecucion...");

    }
}
