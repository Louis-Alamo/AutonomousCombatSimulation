package com.simu.core.factory;

import com.simu.core.entity.move.Move;
import com.simu.core.enums.ElementType;
import com.simu.core.enums.MoveCategory;
import com.simu.core.enums.MoveID;

public class MoveFactory {

    public static Move createMove(MoveID moveId) {

        return switch (moveId) {

            case TACKLE -> Move.builder()
                    .moveName("Tackle")
                    .movePower(40)
                    .moveCategory(MoveCategory.PHYSICAL)
                    .pp(35)
                    .accuracy(1.0)
                    .elementType(ElementType.NORMAL)
                    .build();

            case SCRATCH -> Move.builder()
                    .moveName("Scratch")
                    .movePower(40)
                    .moveCategory(MoveCategory.PHYSICAL)
                    .pp(35)
                    .accuracy(1.0)
                    .elementType(ElementType.NORMAL)
                    .build();

            case QUICK_ATTACK -> Move.builder()
                    .moveName("Quick Attack")
                    .movePower(40)
                    .moveCategory(MoveCategory.PHYSICAL)
                    .pp(30)
                    .accuracy(1.0)
                    .elementType(ElementType.NORMAL)
                    .build();

            case HEADBUTT -> Move.builder()
                    .moveName("Headbutt")
                    .movePower(70)
                    .moveCategory(MoveCategory.PHYSICAL)
                    .pp(15)
                    .accuracy(1.0)
                    .elementType(ElementType.NORMAL)
                    .build();

            case EMBER -> Move.builder()
                    .moveName("Ember")
                    .movePower(40)
                    .moveCategory(MoveCategory.SPECIAL)
                    .pp(25)
                    .accuracy(1.0)
                    .elementType(ElementType.FIRE)
                    .build();

            case FLAME_BURST -> Move.builder()
                    .moveName("Flame Burst")
                    .movePower(70)
                    .moveCategory(MoveCategory.SPECIAL)
                    .pp(15)
                    .accuracy(1.0)
                    .elementType(ElementType.FIRE)
                    .build();

            case WATER_GUN -> Move.builder()
                    .moveName("Water Gun")
                    .movePower(40)
                    .moveCategory(MoveCategory.SPECIAL)
                    .pp(25)
                    .accuracy(1.0)
                    .elementType(ElementType.WATER)
                    .build();

            case BUBBLE_BEAM -> Move.builder()
                    .moveName("Bubble Beam")
                    .movePower(65)
                    .moveCategory(MoveCategory.SPECIAL)
                    .pp(20)
                    .accuracy(1.0)
                    .elementType(ElementType.WATER)
                    .build();

            case VINE_WHIP -> Move.builder()
                    .moveName("Vine Whip")
                    .movePower(45)
                    .moveCategory(MoveCategory.PHYSICAL)
                    .pp(25)
                    .accuracy(1.0)
                    .elementType(ElementType.GRASS)
                    .build();

            case RAZOR_LEAF -> Move.builder()
                    .moveName("Razor Leaf")
                    .movePower(55)
                    .moveCategory(MoveCategory.PHYSICAL)
                    .pp(25)
                    .accuracy(0.95)
                    .elementType(ElementType.GRASS)
                    .build();

            case SEED_BOMB -> Move.builder()
                    .moveName("Seed Bomb")
                    .movePower(80)
                    .moveCategory(MoveCategory.PHYSICAL)
                    .pp(15)
                    .accuracy(1.0)
                    .elementType(ElementType.GRASS)
                    .build();

            case THUNDER_SHOCK -> Move.builder()
                    .moveName("Thunder Shock")
                    .movePower(40)
                    .moveCategory(MoveCategory.SPECIAL)
                    .pp(30)
                    .accuracy(1.0)
                    .elementType(ElementType.ELECTRIC)
                    .build();

            case THUNDERBOLT -> Move.builder()
                    .moveName("Thunderbolt")
                    .movePower(90)
                    .moveCategory(MoveCategory.SPECIAL)
                    .pp(15)
                    .accuracy(1.0)
                    .elementType(ElementType.ELECTRIC)
                    .build();

            case BITE -> Move.builder()
                    .moveName("Bite")
                    .movePower(60)
                    .moveCategory(MoveCategory.PHYSICAL)
                    .pp(25)
                    .accuracy(1.0)
                    .elementType(ElementType.DARK)
                    .build();

            case IRON_TAIL -> Move.builder()
                    .moveName("Iron Tail")
                    .movePower(100)
                    .moveCategory(MoveCategory.PHYSICAL)
                    .pp(15)
                    .accuracy(0.75)
                    .elementType(ElementType.STEEL)
                    .build();

            case PSYCHIC -> Move.builder()
                    .moveName("Psychic")
                    .movePower(90)
                    .moveCategory(MoveCategory.SPECIAL)
                    .pp(10)
                    .accuracy(1.0)
                    .elementType(ElementType.PSYCHIC)
                    .build();

            case EXECUTION -> Move.builder()
                    .moveName("Execution")
                    .movePower(50)
                    .moveCategory(MoveCategory.TRUE_DAMAGE)
                    .pp(5)
                    .accuracy(1.0)
                    .elementType(ElementType.NORMAL)
                    .build();
        };
    }
}
