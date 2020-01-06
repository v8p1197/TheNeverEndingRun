package it.unisa.theneverendingrun.models.powerup.strategies;

import it.unisa.theneverendingrun.models.hero.AbstractHero;

public abstract class PowerUpStrategy {

    protected AbstractHero hero;

    public PowerUpStrategy(AbstractHero hero) {
        this.hero = hero;
    }

    public abstract boolean has();

    public abstract boolean collect();

    public abstract boolean consume();
}
