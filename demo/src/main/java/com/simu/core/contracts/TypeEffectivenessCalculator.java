package com.simu.core.contracts;

import com.simu.core.entity.creature.Creature;
import com.simu.core.entity.move.Move;

public interface TypeEffectivenessCalculator {


    double calculateTypeMultiplier(Creature target, Move moveAttacker);


}
