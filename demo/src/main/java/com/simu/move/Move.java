package com.simu.move;

public class Move {

    private String moveName;
    private double movePower;

    public Move(String moveName, double movePower) {
        this.moveName = moveName;
        this.movePower = movePower;

    }

    public String getMoveName() {
        return this.moveName;
    }

    public double getMovePower() {
        return this.movePower;
    }
}
