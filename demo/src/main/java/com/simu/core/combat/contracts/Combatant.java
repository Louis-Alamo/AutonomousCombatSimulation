package com.simu.core.combat.contracts;

import com.simu.core.combat.creatures.Creature;

public interface Combatant {

    int MAX_TYPES = 2;
    int MAX_MOVES = 4;

    double STAB_MULTIPLIER = 1.5;

    void attack(Creature creature);

    void takeDamage(double damage);
}
