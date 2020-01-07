package it.unisa.theneverendingrun.models.powerup.strategies;

import it.unisa.theneverendingrun.models.hero.Hero;

public abstract class PowerUpStrategy {

    protected Hero hero;

    public PowerUpStrategy(Hero hero) {
        this.hero = hero;
    }

    public abstract boolean has();

    public abstract boolean collect();

    public abstract boolean consume();
}
