package com.simu.core.contracts;

import com.simu.core.entity.creature.Creature;
import com.simu.core.entity.move.Move;

public interface StabCalculator {
    double STAB_MULTIPLIER = 1.5;
    double calculateStabMultiplier(Creature attacker, Move move);
}
