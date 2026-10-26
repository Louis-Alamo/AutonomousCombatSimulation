package com.simu.core.service;

import com.simu.core.contracts.DamageCalculator;
import com.simu.core.contracts.StabCalculator;
import com.simu.core.contracts.TypeEffectivenessCalculator;
import com.simu.core.entity.creature.Creature;
import com.simu.core.entity.move.Move;
import com.simu.core.enums.LogType;
import com.simu.core.logger.AbstractLogger;

public class DamageCalculatorService implements DamageCalculator {

    private final StabCalculator stabCalculator;
    private final TypeEffectivenessCalculator typeEffectivenessCalculator;
    private final AbstractLogger logger;

    public DamageCalculatorService(AbstractLogger logger, StabCalculator stabCalculator, TypeEffectivenessCalculator typeEffectivenessCalculator) {
        this.stabCalculator = stabCalculator;
        this.typeEffectivenessCalculator = typeEffectivenessCalculator;
        this.logger = logger;
    }

    @Override
    public double calculateDamage(Creature attacker, Creature target, Move move) {

        double baseDamage = this.calculateDamageForTypeMove(attacker, target, move);

        this.logger.log(LogType.DEBUG, move.getMoveName() + "'s base damage was " + baseDamage);

        double typeMultiplier = this.typeEffectivenessCalculator.calculateTypeMultiplier(target, move);

        double stabMultiplier = this.stabCalculator.calculateStabMultiplier(attacker, move);

        double finalDamage =  baseDamage * typeMultiplier * stabMultiplier;


        if (stabMultiplier > 1.0) {
            this.logger.log(LogType.DEBUG, move.getMoveName() + " received the STAB bonus. Damage increased from " + baseDamage + " -> " + finalDamage);

        }

        if (typeMultiplier < 1.0) {
            this.logger.log(LogType.BATTLE,  " It's not very effective...");

        } else if (typeMultiplier > 2.0) {
            this.logger.log(LogType.BATTLE, " It's super effective!");

        }

        return finalDamage;
    }

    private double calculateDamageForTypeMove(Creature attacker, Creature target, Move move) {
        return switch (move.getCategory()) {
            case PHYSICAL ->
                    ((DamageCalculator.SCALE_DAMAGE * move.getMovePower()) + 6 ) * ((2.0 * attacker.getAttack()) / (attacker.getAttack() + target.getDefense()));

//                move.getMovePower() * (attacker.getAttack() / target.getDefense()) * DamageCalculator.SCALE_DAMAGE;
            case SPECIAL ->
                    ((DamageCalculator.SCALE_DAMAGE * move.getMovePower()) + 6 ) * ((2.0 * attacker.getSpecialAttack()) / (attacker.getSpecialAttack() + target.getSpecialDefense()));
//                move.getMovePower() * (attacker.getSpecialAttack() / target.getSpecialDefense()) * DamageCalculator.SCALE_DAMAGE;
            case TRUE_DAMAGE -> move.getMovePower() * DamageCalculator.SCALE_DAMAGE;
        };
    }

}
