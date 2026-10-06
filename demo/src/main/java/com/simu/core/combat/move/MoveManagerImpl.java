package com.simu.core.combat.move;

import java.util.List;

import com.simu.core.combat.contracts.MoveManager;

public class MoveManagerImpl implements MoveManager {

    List<Move> moves;
    List<Move> movesAvailables;

    @Override
    public boolean hasUsableMoves() {

        this.movesAvailables = this.moves.stream().filter(Move::isAvailable).toList();
        return !this.movesAvailables.isEmpty();

    }

    @Override
    public void addMoves(List<Move> moves) {

        this.moves = moves;
    }

    @Override
    public Move chooseMove() {
        this.movesAvailables = this.moves.stream().filter(Move::isAvailable).toList();

        if (movesAvailables.size() == 1) {
            return this.moves.getFirst();
        }

        int moveNumberRandom = (int) (Math.random() * this.moves.size());

        Move move = this.moves.get(moveNumberRandom);
        move.consumePP();

        return move;
    }

    @Override
    public List<Move> getMoves() {
        return this.moves;
    }
}
