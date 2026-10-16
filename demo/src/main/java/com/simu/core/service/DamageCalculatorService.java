package com.simu.core.service;

import com.simu.core.contracts.DamageCalculator;
import com.simu.core.contracts.StabCalculator;
import com.simu.core.contracts.TypeEffectivenessCalculator;
import com.simu.core.entity.creature.Creature;
import com.simu.core.entity.move.Move;

public class DamageCalculatorService implements DamageCalculator {

    private final StabCalculator stabCalculator;
    private final TypeEffectivenessCalculator typeEffectivenessCalculator;

    public DamageCalculatorService(StabCalculator stabCalculator,
            TypeEffectivenessCalculator typeEffectivenessCalculator) {
        this.stabCalculator = stabCalculator;
        this.typeEffectivenessCalculator = typeEffectivenessCalculator;
    }

    @Override
    public double calculateDamage(Creature attacker, Creature target, Move move) {

        double baseDamage = this.calculateDamageForTypeMove(attacker, target, move);

        return baseDamage
                * this.typeEffectivenessCalculator.calculateTypeMultiplier(target, move)
                * this.stabCalculator.calculateStabMultiplier(attacker, move);

    }

    private double calculateDamageForTypeMove(Creature attacker, Creature target, Move move) {
        return switch (move.getCategory()) {
            case PHYSICAL ->
                move.getMovePower() * (attacker.getAttack() / target.getDefense()) * DamageCalculator.SCALE_DAMAGE;
            case SPECIAL ->
                move.getMovePower() * (attacker.getSpecialAttack() / target.getSpecialDefense())
                        * DamageCalculator.SCALE_DAMAGE;
            case TRUE_DAMAGE -> move.getMovePower() * DamageCalculator.SCALE_DAMAGE;
            default -> 0.0;
        };
    }

}
