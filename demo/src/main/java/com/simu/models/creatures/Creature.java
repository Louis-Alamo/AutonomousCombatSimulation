package com.simu.models.creatures;

import java.util.List;

import com.simu.contracts.Combatant;
import com.simu.contracts.MoveManager;
import com.simu.move.Move;
import com.simu.move.MoveManagerImpl;
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

        this.logger.debugLog(move.getMoveName() + " category: " + move.getCategory());

        if (!this.doesMoveHit(move)) {
            this.logger.battleLog(this.creatureName + " used " + move.getMoveName() + " ,but it missed!");
            return;
        }

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
            default -> 0.0;

        };
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
        this.moveManager = new MoveManagerImpl();
        this.moveManager.addMoves(movesList);
    }
}