package com.simu.core.contracts;

import com.simu.core.entity.creature.Creature;
import com.simu.core.entity.move.Move;

public interface CombatManager {

    void attack(Creature attacker, Creature defense, Move move);

}
