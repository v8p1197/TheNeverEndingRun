package it.unisa.theneverendingrun.models.powerup.strategies;

import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.powerup.AbstractPowerUp;
import it.unisa.theneverendingrun.models.powerup.PowerUpType;
import it.unisa.theneverendingrun.models.powerup.strategies.impls.MultiplierPowerUpStrategy;
import it.unisa.theneverendingrun.models.powerup.strategies.impls.ShieldPowerUpStrategy;
import it.unisa.theneverendingrun.models.powerup.strategies.impls.SwordPowerUpStrategy;

public class PowerUpStrategyFactory {

    private Hero hero;

    public PowerUpStrategyFactory(Hero hero) {
        this.hero = hero;
    }

    public PowerUpStrategy createPowerUpStrategy(PowerUpType type, AbstractPowerUp abstractPowerUp) {
        switch (type) {
            case SWORD:
                return new SwordPowerUpStrategy(hero, abstractPowerUp);
            case SHIELD:
                return new ShieldPowerUpStrategy(hero, abstractPowerUp);
            case MULTIPLIER:
                return new MultiplierPowerUpStrategy(hero, abstractPowerUp);
            default:
                throw new IllegalArgumentException("Power-up type not valid");
        }
    }
}
