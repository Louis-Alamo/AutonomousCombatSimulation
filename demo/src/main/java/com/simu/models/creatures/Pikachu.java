package com.simu.models.creatures;

public class Pikachu extends Creature {

    public Pikachu() {
        super(35, 55, 40, 50, 50, 90);
        this.setCreatureName("Pikachu");

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
