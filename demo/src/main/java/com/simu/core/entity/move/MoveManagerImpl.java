package com.simu.core.entity.move;

import java.util.ArrayList;
import java.util.List;

import com.simu.core.contracts.MoveManager;
import com.simu.core.enums.MoveID;
import com.simu.core.factory.MoveFactory;

public class MoveManagerImpl implements MoveManager {

    private List<Move> moves = new ArrayList<>();;
    private List<Move> movesAvailables = new ArrayList<>();


    public MoveManagerImpl(MoveID...moveIDS) {

        for(MoveID moveId: moveIDS) {
            this.moves.add(
                    MoveFactory.createMove(moveId)
            );
        }

    }

    public MoveManagerImpl() {}

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
