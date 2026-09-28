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

    public boolean isAlive;

    public Creature(Integer health, Integer baseAttack, ConsoleLogger logger) {

        this.health = health;
        this.baseAttack = baseAttack;

        this.logger = logger;
    }

    /**
     * Behaviors
     */

    @Override
    public void attack(Creature creature) {
        this.logger.battleLog(this.creatureName + " attacked " + creature.creatureName);
        creature.takeDamage(this.baseAttack);
    }

    @Override
    public void takeDamage(Integer damage) {

        this.setHealth(damage);
        this.logger.battleLog(this.creatureName + "");

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

    /**
     * Setters
     */
    public void setHealth(Integer damage) {
        this.health -= damage;
    }

}