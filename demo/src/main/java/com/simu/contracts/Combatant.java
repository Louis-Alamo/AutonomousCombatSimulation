package com.simu.contracts;

import com.simu.models.creatures.Creature;

public interface Combatant {

    void attack(Creature creature);

    void takeDamage(Integer damage);
}
