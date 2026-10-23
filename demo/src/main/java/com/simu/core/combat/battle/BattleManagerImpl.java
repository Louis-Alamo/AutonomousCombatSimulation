package com.simu.core.combat.battle;

import com.simu.core.combat.component.health.HealthComponent;
import com.simu.core.contracts.BattleManager;
import com.simu.core.contracts.CombatManager;
import com.simu.core.dao.AddSuscriberToComponents;
import com.simu.core.entity.creature.Creature;
import com.simu.core.entity.move.Move;
import com.simu.core.enums.LogType;
import com.simu.core.event.Event;
import com.simu.core.event.Observer;
import com.simu.core.event.event.CreatureDeadEvent;
import com.simu.core.logger.AbstractLogger;
import com.simu.core.turn.TurnSelector;

import java.util.List;

public class BattleManagerImpl implements BattleManager, Observer {

    private final CombatManager combatManager;
    private final TurnSelector turnSelector;
    private final List<Creature> creatures;
    private final AbstractLogger logger;

    private boolean isEndBattle = false;


    public BattleManagerImpl(AbstractLogger logger, CombatManager combatManager, TurnSelector turnSelector, List<Creature> creatures) {
        this.logger = logger;
        this.combatManager = combatManager;
        this.turnSelector = turnSelector;
        this.creatures = creatures;

        this.creatures.forEach(
                e -> e.addSubcribersToComponents(
                        new AddSuscriberToComponents(HealthComponent.class, this)
                )
        );
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

            this.logger.log(LogType.BATTLE, "It's " + creatureAttacker.getCreatureName() + "'s turn");
            this.logger.log(LogType.BATTLE, creatureAttacker.getCreatureName() + " used " + move.getMoveName() + " !");

            this.combatManager.attack(creatureAttacker, creatureDefender, move);


        }

        this.endBattle();





    }

    @Override
    public void endBattle() {

        Creature creatureWins = this.creatures.stream().filter(Creature::isAlive).findFirst().orElseThrow();

        for (Creature creature: this.creatures) {
            this.logger.log(LogType.DEBUG, creature.getCreatureName() + " remaining health is " + creature.getCurrentHealth());
        }

        this.logger.log(LogType.BATTLE, creatureWins.getCreatureName() + " won the battle!!!");

    }


    @Override
    public void update(Event event) {

        if (event instanceof CreatureDeadEvent) {
            //this.isEndBattle = true;
        }
    }


}
