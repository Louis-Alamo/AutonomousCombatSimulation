package com.simu.models.creatures;

import com.simu.utils.ConsoleLogger;

public class Pikachu extends Creature {

    public Pikachu(ConsoleLogger logger) {

        super(100, 20, logger);

    }
    /**
     * 
     * 
     * @Override
     *           public Integer atack() {
     * 
     *           Integer attack = this.getBaseAttack() * 1;
     *           System.out.println("Pikachu ataco (ataque: " + attack + ") ");
     *           return attack;
     *           }
     * 
     * @Override
     *           public void takeDamage(Integer damage) {
     *           this.setHealth(damage);
     *           System.out.println("Pikachu recibio daño (vida: " +
     *           this.getHealth() + ")");
     *           }
     */
}
