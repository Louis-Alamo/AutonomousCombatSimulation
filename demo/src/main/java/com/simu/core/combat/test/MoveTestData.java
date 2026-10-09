package com.simu.core.combat.test;

import java.util.ArrayList;
import java.util.List;

import com.simu.core.combat.enums.MoveCategory;
import com.simu.core.combat.move.Move;

public class MoveTestData {

    public static ArrayList<Move> getMovesList(int moveCount) {

        ArrayList<Move> moves = new ArrayList<>(
                List.of(
                        new Move("Bite", 8, 15, 0.40, MoveCategory.PHYSICAL),
                        new Move("Scratch", 6, 25, 0.60, MoveCategory.PHYSICAL),
                        new Move("Tackle", 7, 20, 0.30, MoveCategory.PHYSICAL),
                        new Move("Whip", 9, 15, 0.50, MoveCategory.PHYSICAL),
                        new Move("Headbutt", 12, 10, 0.20, MoveCategory.PHYSICAL),

                        new Move("Energy Blast", 10, 15, 0.45, MoveCategory.SPECIAL),
                        new Move("Shock Wave", 11, 10, 0.35, MoveCategory.SPECIAL),
                        new Move("Flame Burst", 14, 5, 0.25, MoveCategory.SPECIAL),
                        new Move("Water Pulse", 13, 10, 0.55, MoveCategory.SPECIAL),
                        new Move("Energy Beam", 16, 5, 0.15, MoveCategory.SPECIAL)));

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
