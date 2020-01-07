package it.unisa.theneverendingrun.models.powerup.strategies.impls;

import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.powerup.strategies.PowerUpStrategy;

public class SwordPowerUpStrategy extends PowerUpStrategy {

    public SwordPowerUpStrategy(Hero hero) {
        super(hero);
    }

    @Override
    public boolean has() {
        return hero.getSwords() > 0;
    }

    @Override
    public boolean collect() {
        var swords = hero.getSwords();

        if (swords < 3) {
            hero.setShields(swords + 1);
            return true;
        }

        return false;
    }

    @Override
    public boolean consume() {
        if (has()) {
            hero.setSwords(hero.getSwords() - 1);
            return true;
        }
        return false;
    }
}
