package com.simu.battleengine;

import com.simu.models.creatures.Creature;
import com.simu.utils.ConsoleLogger;

public class BattleManagerImpl implements BattleManager {

    private final ConsoleLogger logger;

    private Creature creatureA, creatureB;

    public BattleManagerImpl(ConsoleLogger logger) {
        this.logger = logger;
    }

    @Override
    public void startBattle(Creature creatureA, Creature creatureB) {
        this.creatureA = creatureA;
        this.creatureB = creatureB;

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
