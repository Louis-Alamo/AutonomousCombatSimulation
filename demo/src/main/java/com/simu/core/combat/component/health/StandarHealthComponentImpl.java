package com.simu.core.combat.component.health;

public class StandarHealthComponentImpl implements HealthComponent{

    private double currentHealth;
    private double max_health;
    private boolean isAlive = true;


    public StandarHealthComponentImpl(double health) {
        this.currentHealth = health;
        this.max_health = health;
    }


    @Override
    public void onDamage() {
        return;
    }

    @Override
    public void takeDamage(double damagePoints) {

        if (!this.isAlive) {
            return;
        }

        this.currentHealth -= damagePoints;

        if (this.currentHealth <= 0) {
            this.isAlive = false;
        }

    }

    @Override
    public boolean isDead() {
        return this.isAlive;
    }

    @Override
    public double getHealth() {
        return this.max_health;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }
}
