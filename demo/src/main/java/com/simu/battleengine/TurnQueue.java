package com.simu.battleengine;

import com.simu.contracts.BattleQueue;
import com.simu.models.creatures.Creature;

import java.util.ArrayList;
import java.util.Collections;

public class TurnQueue implements BattleQueue {

    private ArrayList<Creature> listCreatures;
    private int currentTurnIndex = 0;

    private boolean isSorted = false;

    public TurnQueue(ArrayList<Creature> creatures) {
        this.listCreatures = creatures;
    }

    public TurnQueue () {
        this.listCreatures = new ArrayList<>();
    }

    @Override
    public Creature getNextCombatant() {

        if (!this.isSorted) {
            this.updateTurnOrder();
        }

        if (this.currentTurnIndex >= this.listCreatures.size()) {
            this.currentTurnIndex = 0;
        }

        return this.listCreatures.get(this.currentTurnIndex++);
    }

    @Override
    public void addCreature(Creature creature) {

        if (creature == null) {
            throw new IllegalArgumentException("Creature canot be null");
        }

        this.listCreatures.add(creature);

    }

    private void updateTurnOrder() {

        //Salimos del metodo cuando solo tiene un contrincante o ninguno no tiene caso la ordenacion
        if(this.listCreatures.size() <= 1) {
            return;
        }
        int bestIndex = 0;
        for (int i = 0; i < this.listCreatures.size() - 1; i++) {
            Creature creatureA = this.listCreatures.get(i);
            bestIndex = i;

            for (int j = i ; j <this.listCreatures.size() - 1; j++) {

                Creature creatureB = this.listCreatures.get(j + 1);

                if (creatureB.getSpeed() > this.listCreatures.get(bestIndex).getSpeed()){
                    bestIndex = j + 1;
                }
            }

            this.listCreatures.set(i, this.listCreatures.get(bestIndex));
            this.listCreatures.set(bestIndex, creatureA);

        }


        this.isSorted = true;
    }



    public int getCurrentTurnIndex() {
        return this.currentTurnIndex;
    }
}
