package com.simu.core.combat.factory;

import com.simu.core.combat.creatures.*;

public class CreatureFactory {

    public static Creature createCreature(String type) {

        switch (type) {

            case "pikachu" -> {
                return new Pikachu();
            }

            case "charmander" -> {
                return new Charmander();
            }

            case "bulbasaur" -> {
                return new Bulbasaur();
            }

            case "squirtle" -> {
                return new Squirtle();
            }

            default -> {
                return new Creature(39, 52, 43, 60, 50, 65);
            }
        }

    }
}
