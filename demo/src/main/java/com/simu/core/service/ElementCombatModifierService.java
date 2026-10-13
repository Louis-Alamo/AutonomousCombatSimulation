package com.simu.core.service;

import com.simu.core.contracts.StabCalculator;
import com.simu.core.contracts.TypeEffectivenessCalculator;
import com.simu.core.entity.creature.Creature;
import com.simu.core.entity.move.Move;
import com.simu.core.enums.ElementType;

public class ElementCombatModifierService implements TypeEffectivenessCalculator, StabCalculator {


    @Override
    public double calculateTypeMultiplier(Creature target, Move moveAttacker) {

        double typeMultiplier = 1.0;

        for (ElementType defenderType: target.getElementTypes()) {
            typeMultiplier *= moveAttacker.getElementType().getMultiplierAgainst(defenderType);
        }

        return typeMultiplier;
    }

    @Override
    public double calculateStabMultiplier(Creature attacker, Move move) {
        ElementType moveElementType = move.getElementType();

        for (ElementType elementType : attacker.getElementTypes()) {
            if (elementType == moveElementType) {

                return this.STAB_MULTIPLIER;
            }
        }

        return 1.0; // <- Default value if the move and creature have not same ElementType
    }




}
