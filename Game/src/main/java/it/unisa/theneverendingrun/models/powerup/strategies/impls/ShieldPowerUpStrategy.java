package it.unisa.theneverendingrun.models.powerup.strategies.impls;

import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.powerup.strategies.PowerUpStrategy;

public class ShieldPowerUpStrategy extends PowerUpStrategy {

    public ShieldPowerUpStrategy(Hero hero) {
        super(hero);
    }

    @Override
    public boolean has() {
        return hero.getShields() > 0;
    }

    @Override
    public boolean collect() {
        var shields = hero.getShields();

        if (shields < 3) {
            hero.setShields(shields + 1);
            return true;
        }

        return false;
    }

    @Override
    public boolean consume() {
        if (has()) {
            hero.setShields(hero.getShields() - 1);
            return true;
        }
        return false;
    }
}
