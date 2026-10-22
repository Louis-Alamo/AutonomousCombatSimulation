package com.simu.core.combat.battle;

import com.simu.core.contracts.CombatManager;
import com.simu.core.contracts.DamageCalculator;

import com.simu.core.entity.creature.Creature;
import com.simu.core.entity.move.Move;
import com.simu.core.enums.LogType;
import com.simu.core.logger.AbstractLogger;

public class CombatManagerImpl implements CombatManager {


    private final DamageCalculator damageCalculator;
    private final AbstractLogger logger;

    public CombatManagerImpl(AbstractLogger logger, DamageCalculator damageCalculator) {
        this.damageCalculator = damageCalculator;
        this.logger = logger;
    }

    @Override
    public void attack(Creature attacker, Creature defense, Move move) {

        if (!doesMoveHit(move)){
            this.logger.log(LogType.BATTLE, attacker.getCreatureName() + " move failed!");
            return;
        }
        double damageCalculated = this.damageCalculator.calculateDamage(attacker, defense, move);

        this.logger.log(LogType.BATTLE, defense.getCreatureName() + " receives " + damageCalculated + " damage!");

        defense.takeDamage(damageCalculated);

    }


    private boolean doesMoveHit(Move move) {
        double randomNumber = Math.random();
        return randomNumber <= move.getAccuracy();
    }
}
