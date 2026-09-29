package com.simu.factory;

import com.simu.models.creatures.Charmander;
import com.simu.models.creatures.Creature;
import com.simu.models.creatures.Pikachu;

public class CreatureFactory {


    public static Creature createCreature(String type) {

        switch (type) {

            case "pikachu" -> {
                return new Pikachu();
            }

            case "charmander" -> {
                return new Charmander();
            }

            default -> {
                return new Creature(100,20);
            }
        }


    }
}
