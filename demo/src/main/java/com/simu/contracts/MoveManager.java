package com.simu.contracts;

import java.util.List;

import com.simu.move.Move;

public interface MoveManager {

    void addMoves(List<Move> moves);

    boolean hasUsableMoves();

    Move chooseMove();

    List<Move> getMoves();
}
