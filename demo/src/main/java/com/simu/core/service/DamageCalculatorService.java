package com.simu.core.service;

import com.simu.core.contracts.DamageCalculator;
import com.simu.core.contracts.StabCalculator;
import com.simu.core.contracts.TypeEffectivenessCalculator;
import com.simu.core.entity.creature.Creature;
import com.simu.core.entity.move.Move;
import com.simu.core.enums.ElementType;
import com.simu.core.enums.MoveCategory;
import jdk.jfr.Category;

public class DamageCalculatorService implements DamageCalculator {


    private final StabCalculator stabCalculator;
    private final TypeEffectivenessCalculator typeEffectivenessCalculator;

    public DamageCalculatorService(StabCalculator stabCalculator, TypeEffectivenessCalculator typeEffectivenessCalculator) {
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
            case PHYSICAL -> move.getMovePower() * (attacker.getAttack() / target.getDefense()) * this.SCALE_DAMAGE ;
            case SPECIAL -> move.getMovePower() * (attacker.getSpecialAttack() / target.getSpecialDefense()) * this.SCALE_DAMAGE;
            case TRUE_DAMAGE -> move.getMovePower() * this.SCALE_DAMAGE;
            default -> 0.0;
        };
    }





}
