package com.simu.core.combat.battle;

import com.simu.core.combat.contracts.BattleManager;
import com.simu.core.combat.contracts.BattleQueue;
import com.simu.core.combat.creatures.Creature;
import com.simu.core.utils.ConsoleLogger;

public class TurnBattleManager implements BattleManager {

    private final BattleQueue battleQueue;
    private final ConsoleLogger logger;
    private Creature creatureA;
    private Creature creatureB;
    private boolean isReady = false;

    public TurnBattleManager(BattleQueue battleQueue, ConsoleLogger logger) {
        this.battleQueue = battleQueue;
        this.logger = logger;
    }

    @Override
    public void addCombatants(Creature creatureA, Creature creatureB) {

        if (creatureA == null || creatureB == null) {
            this.logger.errorLog("Combatants cannot be null.");
            throw new IllegalArgumentException("Combatants cannot be null.");
        }
        this.battleQueue.addCreature(creatureA);
        this.battleQueue.addCreature(creatureB);

        this.creatureA = creatureA;
        this.creatureB = creatureB;

        this.isReady = true;

        this.logger.debugLog("TurnBattleManager ready");

    }

    @Override
    public void startBattle() {
        if (!this.isReady) {
            this.logger.errorLog("Battle is not ready to start. Combatants are required.");
            throw new IllegalStateException("Battle is not ready to start. Combatants are required.");
        }
        logger.battleLog("The battle begins");
        int cont = 0;
        while (this.creatureA.isAlive() && this.creatureB.isAlive()) {

            if (!this.creatureA.isCanFight() && !this.creatureB.isCanFight()) {
                this.endBattle();
            }
            Creature creature = this.battleQueue.getNextCombatant();

            if (this.battleQueue.isRoundActive() && cont < 1) {
                this.logger.battleLog("Round " + this.battleQueue.getCurrentRound() + " begins");
                ++cont;
            }

            // this.logger.battleLog("Round " + this.battleQueue.getCurrentRound() + "
            // begins");

            this.logger.battleLog("It's " + creature.getCreatureName() + "'s " + " turn.");

            if (creature == this.creatureA) {
                this.creatureA.attack(this.creatureB);

            } else if (creature == this.creatureB) {
                this.creatureB.attack(this.creatureA);

            }

            if (!this.battleQueue.isRoundActive()) {
                cont = 0;
                this.logger.battleLog("Round " + this.battleQueue.getCurrentRound() + " ends!");
            }



        }



        this.endBattle();

    }

    @Override
    public void endBattle() {
        this.logger.battleLog("The battle ended");

        if (!this.creatureA.isCanFight() && !this.creatureB.isCanFight()) {
            this.logger.battleLog("The battle ended in a draw!");
        }

        if (this.creatureA.isAlive()) {

            this.logger.battleLog(this.creatureA.getCreatureName() + " WINS");

        } else {
            this.logger.battleLog(this.creatureB.getCreatureName() + " WINS");
        }

    }
}
