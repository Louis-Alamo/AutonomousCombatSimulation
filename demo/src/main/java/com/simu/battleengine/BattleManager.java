package com.simu.battleengine;

import com.simu.models.creatures.Creature;

public interface BattleManager {

    void startBattle(Creature creatureA, Creature creatureB);

    void endBattle();

}
