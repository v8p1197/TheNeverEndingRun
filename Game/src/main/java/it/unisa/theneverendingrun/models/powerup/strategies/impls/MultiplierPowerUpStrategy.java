package it.unisa.theneverendingrun.models.powerup.strategies.impls;

import it.unisa.theneverendingrun.engine.state.PlayState;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.powerup.AbstractPowerUp;
import it.unisa.theneverendingrun.models.powerup.impls.MultiplierPowerUp;
import it.unisa.theneverendingrun.models.powerup.strategies.PowerUpStrategy;
import it.unisa.theneverendingrun.services.meters.MetersEventType;

public class MultiplierPowerUpStrategy extends PowerUpStrategy {

    public static int MAXIMUM_METERS = 100;

    public MultiplierPowerUpStrategy(Hero hero, AbstractPowerUp abstractPowerUp) {
        super(hero, abstractPowerUp);

        PlayState.meterEditor.getEventManager().subscribe(MetersEventType.METERS_CHANGED,
                MultiplierPowerUpMetersListener.getInstance());
    }

    @Override
    public boolean has() {
        return MultiplierPowerUpMetersListener.getInstance().getRemainingMeters() > 0;
    }

    @Override
    public boolean collect() {
        var previousGameMultiplier = PlayState.scoreMetersListener.getMultiplier();
        var powerUpMultiplierValue = ((MultiplierPowerUp) abstractPowerUp).getValue();

        var oldMultiplier = MultiplierPowerUpMetersListener.getInstance().getMultiplier();
        var newMultiplier = Math.max(MultiplierPowerUpMetersListener.getInstance().getMultiplier(), powerUpMultiplierValue);
        MultiplierPowerUpMetersListener.getInstance().setMultiplier(newMultiplier);

        PlayState.scoreMetersListener.setMultiplier(previousGameMultiplier / oldMultiplier * newMultiplier);

        MultiplierPowerUpMetersListener.getInstance().setRemainingMeters(MAXIMUM_METERS);

        return true;
    }

    @Override
    public boolean consume() {
        return false;
    }
}
