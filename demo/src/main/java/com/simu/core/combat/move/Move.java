package com.simu.core.combat.move;

import com.simu.core.combat.enums.MoveCategory;

public class Move {

    private String moveName;
    private double movePower;
    private MoveCategory category;
    private int currenPP;
    private int pp;
    private double accuracy;

    private boolean isAvailable = true;

    private Move(Builder builder) {
        this.moveName = builder.moveName;
        this.movePower = builder.movePower;
        this.category = builder.moveCategory;
        this.pp = builder.pp;
        this.currenPP = builder.pp;
        this.accuracy = builder.accuracy;
    }

    public static Builder builder() {
        return new Builder();
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

    public double getAccuracy() {
        return this.accuracy;
    }


    //Builder
    public static class Builder {

        private String moveName;
        private double movePower;
        private MoveCategory moveCategory;
        private int pp;
        private double accuracy;

        public Builder moveName(String moveName) {
            this.moveName = moveName;
            return this;
        }

        public Builder movePower(double movePower) {
            this.movePower = movePower;
            return this;
        }

        public Builder moveCategory(MoveCategory moveCategory) {
            this.moveCategory = moveCategory;
            return this;
        }

        public Builder pp(int pp) {
            this.pp = pp;
            return this;
        }

        public Builder accuracy(double accuracy) {
            this.accuracy = accuracy;
            return this;
        }

        public Move build() {
            return new Move(this);
        }
    }

}


