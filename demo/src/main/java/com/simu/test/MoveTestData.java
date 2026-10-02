package com.simu.test;

import java.util.ArrayList;
import java.util.List;

import com.simu.move.Move;

public class MoveTestData {

    public static ArrayList<Move> getMovesList(int moveCount) {

        ArrayList<Move> moves = new ArrayList<>(
                List.of(
                        new Move("Bite", 8),
                        new Move("Scratch", 6),
                        new Move("Tackle", 7),
                        new Move("Whip", 9),
                        new Move("Headbutt", 12),
                        new Move("Claw", 10),
                        new Move("Kick", 11),
                        new Move("Slam", 14),
                        new Move("Charge", 13),
                        new Move("Crush", 16)

                ));

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
