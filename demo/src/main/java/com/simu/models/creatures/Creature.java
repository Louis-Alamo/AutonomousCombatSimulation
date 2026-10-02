package com.simu.models.creatures;

import java.util.ArrayList;

import com.simu.contracts.Combatant;
import com.simu.enums.MoveCategory;
import com.simu.move.Move;
import com.simu.utils.ConsoleLogger;

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

    private double baseAttack = 10.0;

    private ArrayList<Move> moves;

    /**
     * state
     */

    private boolean isAlive;

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
    }

    /**
     * Behaviors
     */

    @Override
    public void attack(Creature target) {

        // Choose move firts
        Move move = this.chooseMove();

        this.logger
                .battleLog(this.creatureName + " used " + move.getMoveName());

        this.logger.debugLog(move.getMoveName() + " category: " + move.getCategory());

        double finalDamage = this.calculateDamage(target, move);

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

    public Move chooseMove() {

        int moveNumberRandom = (int) (Math.random() * this.moves.size());

        return this.moves.get(moveNumberRandom);
    }

    private double calculateDamage(Creature target, Move move) {

        return switch (move.getCategory()) {
            case PHYSICAL -> move.getMovePower() * (this.attack / target.getDefense());
            case SPECIAL -> move.getMovePower() * (this.specialAttack / target.getSpecialDefense());
            default -> 0.0;

        };
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

    public ArrayList<Move> getMoves() {
        return this.moves;
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

    public void setMoves(ArrayList<Move> movesList) {
        this.moves = movesList;
    }
}