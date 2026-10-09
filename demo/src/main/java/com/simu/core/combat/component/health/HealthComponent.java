package com.simu.core.combat.component.health;

public interface HealthComponent {

    void onDamage();

    void takeDamage(double damagePoints);

    boolean isDead();

}
