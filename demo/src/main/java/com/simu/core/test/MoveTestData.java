package com.simu.core.test;

import java.util.ArrayList;
import java.util.List;

import com.simu.core.enums.ElementType;
import com.simu.core.enums.MoveCategory;
import com.simu.core.entity.move.Move;

public class MoveTestData {

    public static ArrayList<Move> getMovesList(int moveCount) {

        List<Move> moves = new ArrayList<>(
                List.of(
                        Move.builder()
                                .moveName("Bite")
                                .movePower(8)
                                .pp(15)
                                .accuracy(0.90)
                                .moveCategory(MoveCategory.PHYSICAL)
                                .elementType(ElementType.DARK)
                                .build(),

                        Move.builder()
                                .moveName("Scratch")
                                .movePower(6)
                                .pp(25)
                                .accuracy(1.00)
                                .moveCategory(MoveCategory.PHYSICAL)
                                .elementType(ElementType.NORMAL)
                                .build(),

                        Move.builder()
                                .moveName("Tackle")
                                .movePower(7)
                                .pp(20)
                                .accuracy(0.95)
                                .moveCategory(MoveCategory.PHYSICAL)
                                .elementType(ElementType.NORMAL)
                                .build(),

                        Move.builder()
                                .moveName("Whip")
                                .movePower(9)
                                .pp(15)
                                .accuracy(0.90)
                                .moveCategory(MoveCategory.PHYSICAL)
                                .elementType(ElementType.GRASS)
                                .build(),

                        Move.builder()
                                .moveName("Headbutt")
                                .movePower(12)
                                .pp(10)
                                .accuracy(0.85)
                                .moveCategory(MoveCategory.PHYSICAL)
                                .elementType(ElementType.NORMAL)
                                .build(),

                        Move.builder()
                                .moveName("Energy Blast")
                                .movePower(10)
                                .pp(15)
                                .accuracy(0.90)
                                .moveCategory(MoveCategory.SPECIAL)
                                .elementType(ElementType.PSYCHIC)
                                .build(),

                        Move.builder()
                                .moveName("Shock Wave")
                                .movePower(11)
                                .pp(10)
                                .accuracy(0.90)
                                .moveCategory(MoveCategory.SPECIAL)
                                .elementType(ElementType.ELECTRIC)
                                .build(),

                        Move.builder()
                                .moveName("Flame Burst")
                                .movePower(14)
                                .pp(5)
                                .accuracy(0.80)
                                .moveCategory(MoveCategory.SPECIAL)
                                .elementType(ElementType.FIRE)
                                .build(),

                        Move.builder()
                                .moveName("Water Pulse")
                                .movePower(13)
                                .pp(10)
                                .accuracy(0.85)
                                .moveCategory(MoveCategory.SPECIAL)
                                .elementType(ElementType.WATER)
                                .build(),

                        Move.builder()
                                .moveName("Energy Beam")
                                .movePower(16)
                                .pp(5)
                                .accuracy(0.75)
                                .moveCategory(MoveCategory.SPECIAL)
                                .elementType(ElementType.GRASS)
                                .build()
                )
        );

        ArrayList<Move> listMoves = new ArrayList<>();

        int cont = 0;

        while (cont <= moveCount) {

            int numberRandom = (int) (Math.random() * moves.size());

            Move move = moves.get(numberRandom);

            for (Move a : listMoves) {
                if (move == a) {
                    break;
                }
            }

            listMoves.add(move);
            cont++;
        }

        return listMoves;

    }

}
