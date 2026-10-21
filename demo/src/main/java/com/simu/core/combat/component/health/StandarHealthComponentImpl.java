package com.simu.core.combat.component.health;

import com.simu.core.event.CommandPublisher;
import com.simu.core.event.Event;
import com.simu.core.event.Observer;
import com.simu.core.event.event.CreatureDeadEvent;

import java.util.ArrayList;
import java.util.List;

public class StandarHealthComponentImpl implements HealthComponent {

    private double currentHealth;
    private double max_health;
    private boolean isAlive = true;

    private List<Observer> subscribers = new ArrayList<>();

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
            this.emit(new CreatureDeadEvent());
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

    @Override
    public void subscribe(Observer observer) {
        if (observer == null) {
            throw new IllegalArgumentException("observer cannot be null...");
        }

        this.subscribers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {

        if (observer == null) {
            throw new IllegalArgumentException("observer object cannot be null...");
        }

        this.subscribers.remove(observer);

    }

    @Override
    public void emit(Event event) {
        this.subscribers.forEach(
                e -> e.update(event)
        );
    }

}
