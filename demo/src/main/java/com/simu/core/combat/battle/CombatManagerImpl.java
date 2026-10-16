package com.simu.core.combat.battle;

import com.simu.core.contracts.CombatManager;
import com.simu.core.contracts.DamageCalculator;

import com.simu.core.entity.creature.Creature;
import com.simu.core.entity.move.Move;

public class CombatManagerImpl implements CombatManager {


    private final DamageCalculator damageCalculator;

    public CombatManagerImpl(DamageCalculator damageCalculator) {
        this.damageCalculator = damageCalculator;

    }

    @Override
    public void attack(Creature attacker, Creature defense, Move move) {

        if (!doesMoveHit(move)){
            return;
        }
        double damageCalculated = this.damageCalculator.calculateDamage(attacker, defense, move);

        defense.takeDamage(damageCalculated);

    }


    private boolean doesMoveHit(Move move) {
        double randomNumber = Math.random();
        return randomNumber <= move.getAccuracy();
    }
}
