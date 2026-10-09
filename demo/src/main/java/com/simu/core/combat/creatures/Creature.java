package com.simu.core.combat.creatures;

import java.util.List;

import com.simu.core.combat.contracts.Combatant;
import com.simu.core.combat.contracts.MoveManager;
import com.simu.core.combat.enums.ElementType;
import com.simu.core.combat.move.MoveManagerImpl;
import com.simu.core.enums.Type;
import com.simu.core.utils.ConsoleLogger;
import com.simu.core.combat.move.Move;

public class Creature implements Combatant {

    /**
     * Generic attributes
     */

    private String creatureName;
    private final ConsoleLogger logger;

    /**
     * Base atributes
     */
    private double health;
    private double currentHealth;
    private double attack;
    private double defense;
    private double specialAttack;
    private double specialDefense;
    private double speed;

    private List<ElementType> elementTypes;

    private MoveManager moveManager;

    /**
     * state
     */

    private boolean isAlive;
    private boolean canFight;

    public Creature(double health, double attack, double defense, double specialAttack, double specialDefense,
            double speed) {

        this.health = health;
        this.currentHealth = health;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;

        this.logger = ConsoleLogger.getInstance();
        this.initializeCreatureStats();

    }

    private void initializeCreatureStats() {
        this.isAlive = true;
        this.canFight = true;
    }

    /**
     * Behaviors
     */

    @Override
    public void attack(Creature target) {

        // Choose move firts
        Move move = this.moveManager.chooseMove();

        if (move == null) {

            this.logger.battleLog(this.creatureName + "has no moves with PP remaining! It loses its turn!");
            return;
        }

        this.logger
                .battleLog(this.creatureName + " used " + move.getMoveName());

        this.logger.debugLog(move.getMoveName() + " category: " + move.getCategory() + " and element type " + move.getElementType());

        if (!this.doesMoveHit(move)) {
            this.logger.battleLog(this.creatureName + " used " + move.getMoveName() + " ,but it missed!");
            return;
        }

        double finalDamage = this.calculateDamage(target, move);
        this.logger.battleLog("Damage calculated " + finalDamage);

        //Calculate the final damage
        finalDamage = finalDamage * this.calculateTypeMultiplier(target, move) * this.calculateStabMultiplier(move);

        logger.battleLog(
                this.creatureName + " dealt " + finalDamage +
                        " damage to " + target.creatureName);

        target.takeDamage(finalDamage);
    }

    @Override
    public void takeDamage(double damage) {
        /**
         * double saludActual = this.getHealth();
         * double salud = saludActual - damage;
         * this.setHealth(salud);
         */

        this.currentHealth -= damage;

        if (this.currentHealth <= 0) {
            this.logger.battleLog(this.creatureName + " has been defeated.");
            this.setAlive(false);
            return;

        }

        this.logger.battleLog(this.creatureName + " now has " + this.getCurrentHealth() + " health");

    }

    /*
     * 
     * Se elimino el metodo y se cambio a la implementacion de MoveManager
     * 
     * public Move chooseMove() {
     * 
     * List<Move> movesAvailable =
     * this.moves.stream().filter(Move::isAvailable).toList();
     * 
     * if (movesAvailable.size() == 0) {
     * return null;
     * }
     * 
     * if (movesAvailable.size() == 1) {
     * return this.moves.get(0);
     * }
     * 
     * int moveNumberRandom = (int) (Math.random() * this.moves.size());
     * 
     * return this.moves.get(moveNumberRandom);
     * 
     * }
     */
    private double calculateDamage(Creature target, Move move) {

        return switch (move.getCategory()) {

            case PHYSICAL -> move.getMovePower() * (this.attack / target.getDefense());
            case SPECIAL -> move.getMovePower() * (this.specialAttack / target.getSpecialDefense());
            case TRUE_DAMAGE ->  move.getMovePower();
            default -> 0.0;

        };
    }

    private double calculateTypeMultiplier(Creature target, Move move) {
        double typeMultiplier  = 1;

        logger.debugLog(
                "Target element types: "
                        + target.getElementTypes()
        );

        for (ElementType defenderType: target.getElementTypes()) {
            logger.debugLog(
                    move.getElementType()
                            + " -> "
                            + defenderType
                            + " = "
                            + move.getElementType()
                            .getMultiplierAgainst(defenderType));

            typeMultiplier  *= move.getElementType().getMultiplierAgainst(defenderType);
        }

        this.logger.debugLog("The move has a " + typeMultiplier + " damage multiplier!");

        return typeMultiplier;
    }

    private double calculateStabMultiplier(Move move) {

        ElementType moveElementType = move.getElementType();

        for (ElementType elementType : this.elementTypes) {
            if (elementType == moveElementType) {
                this.logger.battleLog("Stab multiplier: " + this.STAB_MULTIPLIER);
                return this.STAB_MULTIPLIER;
            }
        }
        this.logger.battleLog("Stab multiplier: 1.0");

        return 1.0; // <- Default value if the move and creature have not same ElementType

    }

    private boolean doesMoveHit(Move move) {

        double randomNumber = (Math.random());
        this.logger
                .debugLog("Probabilidad del movimiento: " + move.getAccuracy() + ", Numero aleatorio: " + randomNumber);

        return randomNumber <= move.getAccuracy();
    }

    /**
     * Getters
     */

    public double getAttack() {
        return this.attack;
    }

    public double getHealth() {
        return this.health;
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

    public double getCurrentHealth() {
        return this.currentHealth;
    }

    public double getSpeed() {
        return this.speed;
    }

    public boolean isAlive() {
        return this.isAlive;
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


}