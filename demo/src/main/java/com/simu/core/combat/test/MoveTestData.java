package com.simu.core.combat.test;

import java.util.ArrayList;
import java.util.List;

import com.simu.core.combat.enums.MoveCategory;
import com.simu.core.combat.move.Move;

public class MoveTestData {

    public static ArrayList<Move> getMovesList(int moveCount) {

        List<Move> moves = new ArrayList<>(
                List.of(
                        Move.builder()
                                .moveName("Bite")
                                .movePower(8)
                                .pp(15)
                                .accuracy(0.40)
                                .moveCategory(MoveCategory.PHYSICAL)
                                .build(),

                        Move.builder()
                                .moveName("Scratch")
                                .movePower(6)
                                .pp(25)
                                .accuracy(0.60)
                                .moveCategory(MoveCategory.PHYSICAL)
                                .build(),

                        Move.builder()
                                .moveName("Tackle")
                                .movePower(7)
                                .pp(20)
                                .accuracy(0.30)
                                .moveCategory(MoveCategory.PHYSICAL)
                                .build(),

                        Move.builder()
                                .moveName("Whip")
                                .movePower(9)
                                .pp(15)
                                .accuracy(0.50)
                                .moveCategory(MoveCategory.PHYSICAL)
                                .build(),

                        Move.builder()
                                .moveName("Headbutt")
                                .movePower(12)
                                .pp(10)
                                .accuracy(0.20)
                                .moveCategory(MoveCategory.PHYSICAL)
                                .build(),

                        Move.builder()
                                .moveName("Energy Blast")
                                .movePower(10)
                                .pp(15)
                                .accuracy(0.45)
                                .moveCategory(MoveCategory.SPECIAL)
                                .build(),

                        Move.builder()
                                .moveName("Shock Wave")
                                .movePower(11)
                                .pp(10)
                                .accuracy(0.35)
                                .moveCategory(MoveCategory.SPECIAL)
                                .build(),

                        Move.builder()
                                .moveName("Flame Burst")
                                .movePower(14)
                                .pp(5)
                                .accuracy(0.25)
                                .moveCategory(MoveCategory.SPECIAL)
                                .build(),

                        Move.builder()
                                .moveName("Water Pulse")
                                .movePower(13)
                                .pp(10)
                                .accuracy(0.55)
                                .moveCategory(MoveCategory.SPECIAL)
                                .build(),

                        Move.builder()
                                .moveName("Energy Beam")
                                .movePower(16)
                                .pp(5)
                                .accuracy(0.15)
                                .moveCategory(MoveCategory.SPECIAL)
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
