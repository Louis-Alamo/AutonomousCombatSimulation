package com.simu.core.combat.contracts;

import com.simu.core.combat.creatures.Creature;

public interface BattleManager {

    void addCombatants(Creature creatureA, Creature creatureB);
    void startBattle();

    void endBattle();

}
