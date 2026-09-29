package com.simu.models.creatures;

import com.simu.contracts.Combatant;
import com.simu.utils.ConsoleLogger;

public class Creature implements Combatant {

    /**
     * Generic attributes
     */

    private String creatureName;
    private ConsoleLogger logger;

    /**
     * Base atributes
     */
    private Integer health;
    private Integer baseAttack;
    private Integer baseDefense;
    private Integer baseSpeed;

    /**
     * state
     */

    private boolean isAlive;

    public Creature(Integer health, Integer baseAttack, ConsoleLogger logger) {

        this.health = health;
        this.baseAttack = baseAttack;

        this.logger = logger;
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
                this.creatureName + " dealt " + this.baseAttack +
                        " damage to " + target.creatureName);

        target.takeDamage(this.baseAttack);

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

    public Integer getBaseAttack() {
        return this.baseAttack;
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