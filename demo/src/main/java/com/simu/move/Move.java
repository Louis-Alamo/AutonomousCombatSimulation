package com.simu.move;

import com.simu.enums.MoveCategory;

public class Move {

    private String moveName;
    private double movePower;
    private MoveCategory category;

    public Move(String moveName, double movePower, MoveCategory category) {
        this.moveName = moveName;
        this.movePower = movePower;
        this.category = category;

    }

    public String getMoveName() {
        return this.moveName;
    }

    public double getMovePower() {
        return this.movePower;
    }

    public MoveCategory getCategory() {
        return this.category;
    }
}
