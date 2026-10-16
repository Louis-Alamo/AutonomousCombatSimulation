package com.simu.core.contracts;

import java.util.List;

import com.simu.core.entity.move.Move;

public interface MoveManager {

    void addMoves(List<Move> moves);

    boolean hasUsableMoves();

    Move chooseMove();

    List<Move> getMoves();
}
