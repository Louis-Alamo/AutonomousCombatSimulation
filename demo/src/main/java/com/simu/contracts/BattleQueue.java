package com.simu.contracts;

import com.simu.models.creatures.Creature;

public interface BattleQueue {


    Creature getNextCombatant();

    void addCreature(Creature creature);

}
