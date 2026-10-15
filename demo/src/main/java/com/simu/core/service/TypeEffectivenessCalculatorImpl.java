package com.simu.core.service;

import com.simu.core.contracts.TypeEffectivenessCalculator;
import com.simu.core.entity.creature.Creature;
import com.simu.core.entity.move.Move;
import com.simu.core.enums.ElementType;

public class TypeEffectivenessCalculatorImpl implements TypeEffectivenessCalculator {


    @Override
    public double calculateTypeMultiplier(Creature target, Move moveAttacker) {

        double typeMultiplier = 1.0;

        for (ElementType defenderType: target.getElementTypes()) {
            typeMultiplier *= moveAttacker.getElementType().getMultiplierAgainst(defenderType);
        }

        return typeMultiplier;
    }






}
