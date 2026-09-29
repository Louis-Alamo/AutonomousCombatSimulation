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
    private Integer health;
    private Integer attack;
    private Integer defense;
    private Integer specialAttack;
    private Integer specialDefense;
    private Integer speed;

    /**
     * state
     */

    private boolean isAlive;

    public Creature(Integer health, Integer attack) {

        this.health = health;
        this.attack = attack;

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

        logger.battleLog(
                this.creatureName + " dealt " + this.attack +
                        " damage to " + target.creatureName);

        target.takeDamage(this.attack);

    }

    @Override
    public void takeDamage(Integer damage) {

        int saludActual = this.getHealth();
        int salud = saludActual - damage;
        this.setHealth(salud);

        if (this.health <= 0) {
            this.logger.battleLog(this.creatureName + " has been defeated.");
            this.setAlive(false);
        }

        this.logger.battleLog(this.creatureName + " now has " + this.getHealth() + " health");

    }

    /**
     * Getters
     */

    public Integer getAttack() {
        return this.attack;
    }

    public Integer getHealth() {
        return this.health;
    }

    public String getCreatureName() {
        return this.creatureName;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    /**
     * Setters
     */
    public void setHealth(Integer health) {
        this.health = health;
    }

    public void setCreatureName(String name) {
        this.creatureName = name;
    }

    public void setAlive(boolean alive) {
        this.isAlive = alive;
    }
}