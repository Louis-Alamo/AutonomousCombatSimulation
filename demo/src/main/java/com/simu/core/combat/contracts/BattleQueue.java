package com.simu.core.combat.contracts;

import com.simu.core.combat.creatures.Creature;

public interface BattleQueue {

    Creature getNextCombatant();

    void addCreature(Creature creature);

    int getCurrentRound();

    boolean isRoundActive();

}
