package com.simu.core.turn;

import com.simu.core.entity.creature.Creature;
import com.simu.core.enums.LogType;
import com.simu.core.logger.AbstractLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpeedTurnSelector implements TurnSelector {

    private List<Creature> creatures;
    private int roundCountIndex = 0;
    private boolean isSorted = false;
    private final AbstractLogger logger;

    public SpeedTurnSelector(AbstractLogger logger, Creature ...creatures) {
        this.logger = logger;
        this.creatures = new ArrayList<>(List.of(creatures));
    }

    private void sortBySpeed(){

        if (this.creatures.size() <= 1) {
            return;
        }


        int bestIndex = 0;

        for (int i = 0; i < this.creatures.size() - 1; i++) {
            bestIndex = i;
            Creature creatureA = this.creatures.get(bestIndex);


            for (int j = i; j < this.creatures.size() - 1; j++) {

                Creature creatureB = this.creatures.get(j + 1);
                if (creatureB.getSpeed() > creatureA.getSpeed()) {
                    bestIndex = j + 1;
                } else if (creatureB.getSpeed() == creatureA.getSpeed()) {
                    double randomNumber = Math.random();

                    if (randomNumber > 0.50) {
                        bestIndex = j + 1;
                    }
                }

            }

            this.creatures.set(i, this.creatures.get(bestIndex));
            this.creatures.set(bestIndex, creatureA);

        }

        this.logger.log(
                LogType.INFO,
                "Turn order has been updated: "
                        + this.creatures.stream().map(Creature::getCreatureName).collect(Collectors.joining(" -> "))
        );


    }


    @Override
    public Creature getNextComabatant() {

        if (!isSorted) {
            this.sortBySpeed();
        }

        if (this.roundCountIndex == this.creatures.size()) {
            this.roundCountIndex = 0;

        }

        int cont = this.roundCountIndex;
        Creature creatureValid = null;

        while (cont < this.creatures.size()) {

            Creature creature = this.creatures.get(roundCountIndex);

            if (creature.isAlive()) {
                this.roundCountIndex++;
                creatureValid = creature;
                break;
            }

            roundCountIndex++;

        }

        if (creatureValid == null) {
            throw new IllegalStateException("No living combatants available. ");
        }

        return creatureValid;
    }
}
