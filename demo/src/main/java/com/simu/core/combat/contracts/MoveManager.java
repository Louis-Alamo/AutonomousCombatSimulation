package com.simu.core.combat.contracts;

import java.util.List;

import com.simu.core.combat.move.Move;

public interface MoveManager {

    void addMoves(List<Move> moves);

    boolean hasUsableMoves();

    Move chooseMove();

    List<Move> getMoves();
}
