package com.simu.core.combat.battle;

import com.simu.core.contracts.BattleManager;
import com.simu.core.contracts.CombatManager;
import com.simu.core.entity.creature.Creature;
import com.simu.core.entity.move.Move;
import com.simu.core.turn.TurnSelector;

import java.util.List;
import java.util.logging.Logger;

public class BattleManagerImpl implements BattleManager {

    private final CombatManager combatManager;
    private final TurnSelector turnSelector;
    private final List<Creature> creatures;


    public BattleManagerImpl(CombatManager combatManager, TurnSelector turnSelector, List<Creature> creatures) {
        this.combatManager = combatManager;
        this.turnSelector = turnSelector;
        this.creatures = creatures;
    }


    private boolean isReady() {

        if (this.creatures.size() < 2) {
            throw new IllegalArgumentException("The battle, needs min 2 combatients...");
        }

        return true;
    }

    private boolean creaturesIsAlive() {
        int cont = 0;

        for (int i = 0; i < this.creatures.size(); i++) {

            if (this.creatures.get(i).isAlive()){
                cont += 1;
            }
        }

        if (cont <= 1) {
            return false;
        }

        return true;
    }


    private Creature selectTarget(Creature attacker ) {
        return this.creatures.stream().filter(creature -> creature != attacker).findFirst().orElseThrow();
    }


    @Override
    public void startBattle() {

        if (!isReady()) {
            return;
        }
        Creature creatureAttacker = null;
        Creature creatureDefender = null;
        Move move = null;

        while (this.creaturesIsAlive()) {

            creatureAttacker = this.turnSelector.getNextComabatant();
            creatureDefender = this.selectTarget(creatureAttacker);
            move = creatureAttacker.selectMove();


            this.combatManager.attack(creatureAttacker, creatureDefender, move);


        }

        this.endBattle();





    }

    @Override
    public void endBattle() {
        System.out.println("Ni perra idea alguien gano, quien? sabe?");
    }
}
