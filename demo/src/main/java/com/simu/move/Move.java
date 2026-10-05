package com.simu.move;

import com.simu.enums.MoveCategory;

public class Move {

    private String moveName;
    private double movePower;
    private MoveCategory category;
    private int currenPP;
    private int pp;

    private boolean isAvailable = true;

    public Move(String moveName, double movePower, int pp, MoveCategory category) {
        this.moveName = moveName;
        this.movePower = movePower;
        this.category = category;
        this.currenPP = pp;
        this.pp = pp;

    }

    public boolean consumePP() {
        if (this.currenPP <= 0) {
            this.isAvailable = false;
            return false;
        }
        this.currenPP -= 1;
        return true;
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

    public boolean isAvailable() {
        return this.isAvailable;
    }

}
