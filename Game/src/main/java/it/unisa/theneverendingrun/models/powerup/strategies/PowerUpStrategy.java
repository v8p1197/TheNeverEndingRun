package it.unisa.theneverendingrun.models.powerup.strategies;

import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.powerup.AbstractPowerUp;

public abstract class PowerUpStrategy {

    protected Hero hero;
    protected AbstractPowerUp abstractPowerUp;

    public PowerUpStrategy(Hero hero, AbstractPowerUp abstractPowerUp) {
        this.hero = hero;
        this.abstractPowerUp = abstractPowerUp;
    }

    public abstract boolean has();

    public abstract boolean collect();

    public abstract boolean consume();
}
