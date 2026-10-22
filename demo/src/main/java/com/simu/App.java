package com.simu;


import com.simu.core.combat.battle.BattleManagerImpl;
import com.simu.core.combat.battle.CombatManagerImpl;
import com.simu.core.contracts.*;
import com.simu.core.entity.creature.Creature;
import com.simu.core.enums.CreatureID;
import com.simu.core.factory.CreatureFactory;
import com.simu.core.logger.AbstractLogger;
import com.simu.core.logger.ConsoleLogger;
import com.simu.core.service.DamageCalculatorService;
import com.simu.core.service.StabCalculatorImpl;
import com.simu.core.service.TypeEffectivenessCalculatorImpl;
import com.simu.core.turn.SpeedTurnSelector;
import com.simu.core.turn.TurnSelector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class App {
    public static void main(String[] args) {



        ArrayList<Creature> creatures = new ArrayList<>(
                List.of(
                        Objects.requireNonNull(CreatureFactory.createCreature(CreatureID.SQUIRTLE)),
                        Objects.requireNonNull(CreatureFactory.createCreature(CreatureID.CHARMANDER)),
                        Objects.requireNonNull(CreatureFactory.createCreature(CreatureID.PIKACHU)),
                        Objects.requireNonNull(CreatureFactory.createCreature(CreatureID.BULBASAUR))
                ));


        creatures.forEach(Creature::creatureDataView);

        //Create dependencies
        AbstractLogger logger = new ConsoleLogger();
        StabCalculator stabCalculator = new StabCalculatorImpl();
        TypeEffectivenessCalculator typeEffectivenessCalculator = new TypeEffectivenessCalculatorImpl();
        DamageCalculator damageCalculator = new DamageCalculatorService(logger, stabCalculator, typeEffectivenessCalculator);

        CombatManager combatManager = new CombatManagerImpl(logger, damageCalculator);

        TurnSelector turnSelector = new SpeedTurnSelector(
                logger,
                creatures.get(0),
                creatures.get(1)
        );

        BattleManager battleManager = new BattleManagerImpl(
                logger,
                combatManager,
                turnSelector,
                new ArrayList<>(List.of(
                            creatures.get(0),
                            creatures.get(1)
                    ))
                );

        battleManager.startBattle();






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
