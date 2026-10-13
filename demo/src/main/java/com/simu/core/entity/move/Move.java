package com.simu.core.entity.move;

import com.simu.core.enums.ElementType;
import com.simu.core.enums.MoveCategory;

public class Move {

    private String moveName;
    private double movePower;
    private MoveCategory category;
    private int currenPP;
    private int pp;
    private double accuracy;
    private ElementType elementType;

    private boolean isAvailable = true;

    private Move(Builder builder) {
        this.moveName = builder.moveName;
        this.movePower = builder.movePower;
        this.category = builder.moveCategory;
        this.pp = builder.pp;
        this.currenPP = builder.pp;
        this.accuracy = builder.accuracy;
        this.elementType = builder.elementType;
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

    public ElementType getElementType() {
        return this.elementType;
    }

    public int getCurrentPP() {
        return this.currenPP;
    }

    public int getPP() {
        return this.pp;
    }

    //Builder
    public static class Builder {

        private String moveName;
        private double movePower;
        private MoveCategory moveCategory;
        private int pp;
        private double accuracy;
        private ElementType elementType;

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

        public Builder elementType(ElementType elementType) {
            this.elementType = elementType;
            return this;
        }

        public Move build() {
            return new Move(this);
        }
    }

}


