package com.simu.core.combat.battle;

import com.simu.core.contracts.BattleManager;
import com.simu.core.entity.creature.Creature;
import com.simu.core.utils.ConsoleLogger;

public class RandomBattleManager implements BattleManager {

    private final ConsoleLogger logger;

    private Creature creatureA, creatureB;

    public RandomBattleManager(ConsoleLogger logger) {
        this.logger = logger;
    }


    @Override
    public void addCombatants(Creature creatureA, Creature creatureB) {

        if (creatureA == null || creatureB == null) {
            throw new IllegalArgumentException("Combatants cannot be null.");
        }

        this.creatureA = creatureA;
        this.creatureB = creatureB;

    }

    @Override
    public void startBattle() {


        int roundCount = 1;

        while (creatureA.isAlive() && creatureB.isAlive()) {
            logger.battleLog("Round " + roundCount + " started");

            int randomNumeroBattle = (int) (Math.random() * 2) + 1;

            if (randomNumeroBattle == 1) {
                this.creatureA.attack(this.creatureB);
                if (!creatureB.isAlive()) {
                    break;
                } else {
                    creatureB.attack(creatureA);
                }

            } else {
                creatureB.attack(creatureA);

                if (!creatureA.isAlive()) {
                    break;
                } else {
                    creatureA.attack(creatureB);

                }

                logger.battleLog("Round " + roundCount + " ended");
                roundCount++;
            }

        }

        this.endBattle();

    }

    @Override
    public void endBattle() {

        if (this.creatureA.isAlive()) {

            this.logger.battleLog(this.creatureA.getCreatureName() + " WINS");

        } else {
            this.logger.battleLog(this.creatureB.getCreatureName() + " WINS");
        }

    }

}
