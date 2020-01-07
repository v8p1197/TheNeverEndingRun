package it.unisa.theneverendingrun.models.powerup.strategies;

import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.powerup.PowerUpType;
import it.unisa.theneverendingrun.models.powerup.strategies.impls.MultiplierPowerUpStrategy;
import it.unisa.theneverendingrun.models.powerup.strategies.impls.ShieldPowerUpStrategy;
import it.unisa.theneverendingrun.models.powerup.strategies.impls.SwordPowerUpStrategy;

public class PowerUpStrategyFactory {

    private Hero hero;

    public PowerUpStrategyFactory(Hero hero) {
        this.hero = hero;
    }

    public PowerUpStrategy createPowerUpStrategy(PowerUpType type) {
        switch (type) {
            case SWORD:
                return new SwordPowerUpStrategy(hero);
            case SHIELD:
                return new ShieldPowerUpStrategy(hero);
            case MULTIPLIER:
                return new MultiplierPowerUpStrategy(hero);
            default:
                throw new IllegalArgumentException("Power-up type not valid");
        }
    }
}
