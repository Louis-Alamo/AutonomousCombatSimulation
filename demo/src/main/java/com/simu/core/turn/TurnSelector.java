package com.simu.core.turn;

import com.simu.core.entity.creature.Creature;

public interface TurnSelector {

    Creature getNextComabatant();
}
