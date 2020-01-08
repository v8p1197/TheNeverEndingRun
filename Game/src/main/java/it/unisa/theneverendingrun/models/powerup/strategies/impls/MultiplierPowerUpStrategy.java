package it.unisa.theneverendingrun.models.powerup.strategies.impls;

import it.unisa.theneverendingrun.engine.state.PlayState;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.powerup.AbstractPowerUp;
import it.unisa.theneverendingrun.models.powerup.impls.MultiplierPowerUp;
import it.unisa.theneverendingrun.models.powerup.strategies.PowerUpStrategy;
import it.unisa.theneverendingrun.services.meters.MetersEventType;
import it.unisa.theneverendingrun.services.meters.MetersListener;

public class MultiplierPowerUpStrategy extends PowerUpStrategy implements MetersListener {

    private final static int MAXIMUM_METERS = 50;

    public static int remainingMeters;

    public static float multiplierValue;

    public MultiplierPowerUpStrategy(Hero hero, AbstractPowerUp abstractPowerUp) {
        super(hero, abstractPowerUp);
        remainingMeters = MAXIMUM_METERS;
        multiplierValue = ((MultiplierPowerUp) abstractPowerUp).getValue();

        PlayState.meterEditor.getEventManager().subscribe(MetersEventType.METERS_CHANGED, this);
    }

    @Override
    public boolean has() {
        return remainingMeters > 0;
    }

    @Override
    public boolean collect() {
        var previousMultiplier = PlayState.scoreMetersListener.getMultiplier();
        PlayState.scoreMetersListener.setMultiplier(previousMultiplier * multiplierValue);

        return true;
    }

    @Override
    public boolean consume() {
        return false;
    }

    @Override
    public void update(MetersEventType eventType, int meters) {
        if (eventType == MetersEventType.METERS_CHANGED) {
            remainingMeters = Math.max(remainingMeters - 1, 0);
            if (remainingMeters == 0) {
                PlayState.meterEditor.getEventManager().unsubscribe(MetersEventType.METERS_CHANGED, this);
                var multiplier = PlayState.scoreMetersListener.getMultiplier();
                PlayState.scoreMetersListener.setMultiplier(multiplier / multiplierValue);
            }
        }
    }
}
