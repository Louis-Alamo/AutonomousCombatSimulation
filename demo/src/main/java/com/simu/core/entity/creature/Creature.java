package com.simu.core.entity.creature;

import java.util.ArrayList;
import java.util.List;

import com.simu.core.combat.component.health.HealthComponent;
import com.simu.core.contracts.IBuilder;
import com.simu.core.contracts.MoveManager;
import com.simu.core.dao.AddSuscriberToComponents;
import com.simu.core.enums.ElementType;
import com.simu.core.entity.move.MoveManagerImpl;
import com.simu.core.enums.MoveID;
import com.simu.core.utils.ConsoleLogger;
import com.simu.core.entity.move.Move;

public class Creature {

    /**
     * Generic attributes
     */

    private final int MAX_MOVES = 4;
    private final int MAX_TYPES = 2;


    private String creatureName;
//    private ConsoleLogger logger;

    /**
     * Base atributes
     */

    private HealthComponent healthComponent;
    private double attack;
    private double defense;
    private double specialAttack;
    private double specialDefense;
    private double speed;

    private List<ElementType> elementTypes;


    private MoveManager moveManager;

    private boolean isAlive = true;
    private boolean canFight = true;

    private Creature(Builder builder) {
        this.creatureName = builder.creatureName;
        this.healthComponent = builder.healthComponent;
        this.attack = builder.attack;
        this.defense = builder.defense;
        this.specialAttack = builder.specialAttack;
        this.specialDefense = builder.specialDefense;
        this.speed = builder.speed;
        this.elementTypes = builder.elementTypes;
        this.moveManager = builder.moveManager;

//        this.logger = ConsoleLogger.getInstance();
    }

    public void creatureDataView(){
            System.out.println("====================================");
            System.out.println("Creature: " + this.creatureName);
            System.out.println("====================================");

            System.out.println("HP: " + this.healthComponent.getHealth());
            System.out.println("Attack: " + this.attack);
            System.out.println("Defense: " + this.defense);
            System.out.println("Special Attack: " + this.specialAttack);
            System.out.println("Special Defense: " + this.specialDefense);
            System.out.println("Speed: " + this.speed);

            System.out.println("\nElement Types:");

            for (ElementType type : this.elementTypes) {
                System.out.println("- " + type);
            }

            System.out.println("\nMoves:");

            for (Move move : this.getMoves()) {
                System.out.println(
                        "- " + move.getMoveName()
                                + " | Type: " + move.getElementType()
                                + " | Power: " + move.getMovePower()
                                + " | PP: " + move.getCurrentPP() + "/" + move.getPP()
                );
            }

            System.out.println("====================================");

    }

    /**
     * Behaviors
     */


//    /**
//    @Override
//    public void attack(Creature target) {
//
//        // Choose move firts
//        Move move = this.moveManager.chooseMove();
//
//        if (move == null) {
//
//            this.logger.battleLog(this.creatureName + "has no moves with PP remaining! It loses its turn!");
//            return;
//        }
//
//        this.logger
//                .battleLog(this.creatureName + " used " + move.getMoveName());
//
//        this.logger.debugLog(
//                move.getMoveName() + " category: " + move.getCategory() + " and element type " + move.getElementType());
//
//        if (!this.doesMoveHit(move)) {
//            this.logger.battleLog(this.creatureName + " used " + move.getMoveName() + " ,but it missed!");
//            return;
//        }
//
//        double finalDamage = this.calculateDamage(target, move); // <---
//        this.logger.battleLog("Damage calculated " + finalDamage);
//
//        // Calculate the final damage
//        finalDamage = finalDamage * this.calculateTypeMultiplier(target, move) * this.calculateStabMultiplier(move);
//
//
//
//        logger.battleLog(
//                this.creatureName + " dealt " + finalDamage +
//                        " damage to " + target.creatureName);
//
//        target.takeDamage(finalDamage);
//    }
//
//
//     */

    public Move selectMove() {
        return this.moveManager.chooseMove();
    }

    public void takeDamage(double damage) {
        /**
         * double saludActual = this.getHealth();
         * double salud = saludActual - damage;
         * this.setHealth(salud);
         */

        /*
         * this.currentHealth -= damage;
         *
         * if (this.currentHealth <= 0) {
         * this.logger.battleLog(this.creatureName + " has been defeated.");
         * this.setAlive(false);
         * return;
         *
         * }
         *
         * this.logger.battleLog(this.creatureName + " now has " +
         * this.getCurrentHealth() + " health");
         */

//        this.logger.battleLog("la creatura" + this.creatureName + " recibio daño");
        this.healthComponent.takeDamage(damage);

    }

    public void addSubcribersToComponents(AddSuscriberToComponents addSuscriberToComponents) {

        if (addSuscriberToComponents.componentClass() == HealthComponent.class) {
            this.healthComponent.subscribe(addSuscriberToComponents.subscriber());
        }
    }



//    /*
//     *
//     * Se elimino el metodo y se cambio a la implementacion de MoveManager
//     *
//     * public Move chooseMove() {
//     *
//     * List<Move> movesAvailable =
//     * this.moves.stream().filter(Move::isAvailable).toList();
//     *
//     * if (movesAvailable.size() == 0) {
//     * return null;
//     * }
//     *
//     * if (movesAvailable.size() == 1) {
//     * return this.moves.get(0);
//     * }
//     *
//     * int moveNumberRandom = (int) (Math.random() * this.moves.size());
//     *
//     * return this.moves.get(moveNumberRandom);
//     *
//     * }
//     */
//    private double calculateDamage(Creature target, Move move) {
//
//        return switch (move.getCategory()) {
//
//            case PHYSICAL -> move.getMovePower() * (this.attack / target.getDefense()) * this.SCALE_DAMAGE ;
//            case SPECIAL -> move.getMovePower() * (this.specialAttack / target.getSpecialDefense()) * this.SCALE_DAMAGE;
//            case TRUE_DAMAGE -> move.getMovePower() * this.SCALE_DAMAGE;
//            default -> 0.0;
//
//        };
//    }

//    private double calculateTypeMultiplier(Creature target, Move move) {
//        double typeMultiplier = 1;
//
//        logger.debugLog(
//                "Target element types: "
//                        + target.getElementTypes());
//
//        for (ElementType defenderType : target.getElementTypes()) {
//            logger.debugLog(
//                    move.getElementType()
//                            + " -> "
//                            + defenderType
//                            + " = "
//                            + move.getElementType()
//                                    .getMultiplierAgainst(defenderType));
//
//            typeMultiplier *= move.getElementType().getMultiplierAgainst(defenderType);
//        }
//
//        this.logger.debugLog("The move has a " + typeMultiplier + " damage multiplier!");
//
//        return typeMultiplier;
//    }

//    private double calculateStabMultiplier(Move move) {
//
//        ElementType moveElementType = move.getElementType();
//
//        for (ElementType elementType : this.elementTypes) {
//            if (elementType == moveElementType) {
//                this.logger.battleLog("Stab multiplier: " + this.STAB_MULTIPLIER);
//                return this.STAB_MULTIPLIER;
//            }
//        }
//        this.logger.battleLog("Stab multiplier: 1.0");
//
//        return 1.0; // <- Default value if the move and creature have not same ElementType
//
//    }

//    private boolean doesMoveHit(Move move) {
//
//        double randomNumber = (Math.random());
//        this.logger
//                .debugLog("Probabilidad del movimiento: " + move.getAccuracy() + ", Numero aleatorio: " + randomNumber);
//
//        return randomNumber <= move.getAccuracy();
//    }



    /**
     * Getters
     */


    public double getAttack() {
        return this.attack;
    }

    public double getDefense() {
        return this.defense;
    }

    public double getSpecialAttack() {
        return this.specialAttack;
    }
    public double getSpecialDefense() {
        return this.specialDefense;
    }

    public String getCreatureName() {
        return this.creatureName;
    }


    public boolean isAlive() {
        return this.healthComponent.isDead();
    }

    public boolean isCanFight() {
        return this.moveManager.hasUsableMoves();

    }

    public List<Move> getMoves() {
        return this.moveManager.getMoves();
    }

    public List<ElementType> getElementTypes() {
        return this.elementTypes;
    }

    /**
     * Setters
     */

    public void setCreatureName(String name) {
        this.creatureName = name;
    }

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }

    public void setMoves(List<Move> movesList) {

        if (movesList.size() > this.MAX_MOVES) {
            throw new IllegalArgumentException("A creature cannot learn more than 4 moves.");
        }
        this.moveManager = new MoveManagerImpl();
        this.moveManager.addMoves(movesList);
    }

    public void setElementTypes(List<ElementType> elementTypes) {

        if (elementTypes.size() > this.MAX_TYPES) {
            throw new IllegalArgumentException("A creature cannot have more than 2 element types");
        }

        this.elementTypes = elementTypes;
    }

    public double getSpeed() {
        return this.speed;
    }

    public static class Builder implements IBuilder {

        private String creatureName;
        private ConsoleLogger logger;

        /**
         * Base atributes
         */
        private HealthComponent healthComponent;
        private double attack;
        private double defense;
        private double specialAttack;
        private double specialDefense;
        private double speed;
        private List<ElementType> elementTypes = new ArrayList<>();
        private MoveManager moveManager;

        private boolean isAlive;
        private boolean canFight;

        public Builder creatureName(String creatureName) {
            this.creatureName = creatureName;
            return this;
        }

        public Builder healthComponent(HealthComponent healthComponent) {
            this.healthComponent = healthComponent;
            return this;
        }

        public Builder attack(double attack) {
            this.attack = attack;
            return this;
        }

        public Builder defense(double defense) {
            this.defense = defense;
            return this;
        }

        public Builder specialAttack(double specialAttack) {
            this.specialAttack = specialAttack;
            return this;
        }

        public Builder specialDefense(double specialDefense) {
            this.specialDefense = specialDefense;
            return this;
        }

        public Builder speed(double speed) {
            this.speed = speed;
            return this;
        }

        public Builder elementTypes(ElementType... types) {

            if (types.length > 2) {
                throw new IllegalArgumentException("A creature cannot have more than 2 element types");
            }
            this.elementTypes.addAll(List.of(types));
            return this;
        }

        public Builder moveManager(MoveID...moveIDS) {
            this.moveManager = new MoveManagerImpl(moveIDS);
            return this;
        }

        @Override
        public Creature build() {
            return new Creature(this);
        }
    }

}