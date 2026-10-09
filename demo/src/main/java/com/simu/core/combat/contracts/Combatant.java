package com.simu.core.combat.contracts;

import com.simu.core.combat.creatures.Creature;

public interface Combatant {

    void attack(Creature creature);

    void takeDamage(double damage);
}
