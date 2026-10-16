
package com.simu.core.factory;

import com.simu.core.combat.component.health.StandarHealthComponentImpl;
import com.simu.core.entity.creature.*;
import com.simu.core.enums.CreatureID;
import com.simu.core.enums.ElementType;
import com.simu.core.enums.MoveID;

public class CreatureFactory {

    public static Creature createCreature(CreatureID creatureID) {

        switch (creatureID) {

            case PIKACHU -> {
                return new Creature.Builder()
                        .creatureName(creatureID.getDisplayName())
                        .healthComponent(new StandarHealthComponentImpl(35))
                        .attack(35)
                        .defense(30)
                        .specialAttack(50)
                        .specialDefense(40)
                        .speed(90)
                        .elementTypes(ElementType.ELECTRIC)
                        .moveManager(
                                MoveID.THUNDERBOLT,
                                MoveID.QUICK_ATTACK,
                                MoveID.THUNDERBOLT,
                                MoveID.IRON_TAIL)
                        .build();
            }

            case CHARMANDER -> {
                return new Creature.Builder()
                        .creatureName(creatureID.getDisplayName())
                        .healthComponent(new StandarHealthComponentImpl(39))
                        .attack(52)
                        .defense(43)
                        .specialAttack(60)
                        .specialDefense(50)
                        .speed(65)
                        .elementTypes(ElementType.FIRE)
                        .moveManager(
                                MoveID.SCRATCH,
                                MoveID.EMBER,
                                MoveID.FLAME_BURST,
                                MoveID.BITE)
                        .build();
            }

            case BULBASAUR -> {
                return new Creature.Builder()
                        .creatureName(creatureID.getDisplayName())
                        .healthComponent(new StandarHealthComponentImpl(39))
                        .attack(52)
                        .defense(43)
                        .specialAttack(60)
                        .specialDefense(50)
                        .speed(65)
                        .elementTypes(ElementType.GRASS, ElementType.POISON)
                        .moveManager(
                                MoveID.TACKLE,
                                MoveID.VINE_WHIP,
                                MoveID.RAZOR_LEAF,
                                MoveID.SEED_BOMB)
                        .build();
            }

            case SQUIRTLE -> {
                return new Creature.Builder()
                        .creatureName(creatureID.getDisplayName())
                        .healthComponent(new StandarHealthComponentImpl(39))
                        .attack(52)
                        .defense(43)
                        .specialAttack(60)
                        .specialDefense(50)
                        .speed(65)
                        .elementTypes(ElementType.WATER)
                        .moveManager(
                                MoveID.TACKLE,
                                MoveID.QUICK_ATTACK,
                                MoveID.THUNDERBOLT,
                                MoveID.IRON_TAIL)
                        .build();
            }
            default -> {
                return null;
            }
        }

    }
}
