package com.simu.core.contracts;

import com.simu.core.entity.creature.Creature;
import com.simu.core.entity.move.Move;

public interface DamageCalculator {

    double SCALE_DAMAGE = 0.50;

    double calculateDamage(Creature attacker, Creature target, Move move);

}
