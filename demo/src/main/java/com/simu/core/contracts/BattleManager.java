package com.simu.core.contracts;

import com.simu.core.entity.creature.Creature;

public interface BattleManager {

    void addCombatants(Creature creatureA, Creature creatureB);
    void startBattle();

    void endBattle();

}
