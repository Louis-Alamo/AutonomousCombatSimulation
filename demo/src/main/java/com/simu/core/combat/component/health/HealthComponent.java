package com.simu.core.combat.component.health;

import com.simu.core.event.CommandPublisher;

public interface HealthComponent extends CommandPublisher {

    void onDamage();

    void takeDamage(double damagePoints);

    boolean isDead();

    double getHealth();

}
