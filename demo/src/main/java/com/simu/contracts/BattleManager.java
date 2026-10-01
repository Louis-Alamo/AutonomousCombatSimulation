package com.simu.contracts;

import com.simu.models.creatures.Creature;

public interface BattleManager {

    void addCombatants(Creature creatureA, Creature creatureB);
    void startBattle();

    void endBattle();

}
