package com.simu.core.factory;

import com.simu.core.entity.creature.*;

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
                return null;
            }
        }

    }
}
