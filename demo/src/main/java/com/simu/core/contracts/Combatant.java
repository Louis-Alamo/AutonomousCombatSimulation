package com.simu.core.contracts;

import com.simu.core.entity.creature.Creature;

public interface Combatant {

    int MAX_TYPES = 2;
    int MAX_MOVES = 4;

    double STAB_MULTIPLIER = 1.5;

    void attack(Creature creature);

    void takeDamage(double damage);
}
