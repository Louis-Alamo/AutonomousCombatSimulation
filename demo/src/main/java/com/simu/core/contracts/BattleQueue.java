package com.simu.core.contracts;

import com.simu.core.entity.creature.Creature;

public interface BattleQueue {

    Creature getNextCombatant();

    void addCreature(Creature creature);

    int getCurrentRound();

    boolean isRoundActive();

}
