package com.simu.models.creatures;

import com.simu.contracts.Combatant;
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

        double finalDamage = this.baseAttack * (this.attack / target.getDefense());

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
        return defense;
    }

    public String getCreatureName() {
        return this.creatureName;
    }

    public double getCurrentHealth(){
        return this.currentHealth;
    }

    public double getSpeed() {
        return this.speed;
    }

    public boolean isAlive() {
        return this.isAlive;
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
}