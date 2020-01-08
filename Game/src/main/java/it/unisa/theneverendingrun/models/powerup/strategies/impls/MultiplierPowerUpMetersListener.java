package it.unisa.theneverendingrun.models.powerup.strategies.impls;

import it.unisa.theneverendingrun.engine.state.PlayState;
import it.unisa.theneverendingrun.services.meters.MetersEventType;
import it.unisa.theneverendingrun.services.meters.MetersListener;

public class MultiplierPowerUpMetersListener implements MetersListener {

    private int remainingMeters;

    public float multiplier;

    private MultiplierPowerUpMetersListener() {
        remainingMeters = 0;
        multiplier = 1;
    }


    private static class MultiplierPowerUpMetersListenerHolder {
        public static MultiplierPowerUpMetersListener instance = new MultiplierPowerUpMetersListener();
    }

    public static MultiplierPowerUpMetersListener getInstance() {
        return MultiplierPowerUpMetersListenerHolder.instance;
    }

    public int getRemainingMeters() {
        return remainingMeters;
    }

    public float getMultiplier() {
        return multiplier;
    }

    public void setRemainingMeters(int remainingMeters) {
        this.remainingMeters = remainingMeters;
    }

    public void setMultiplier(float multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public void update(MetersEventType eventType, int meters) {
        if (eventType == MetersEventType.METERS_CHANGED) {
            getInstance().setRemainingMeters(Math.max(getInstance().getRemainingMeters() - 1, 0));
            System.out.println(meters + " " + getInstance().getRemainingMeters());
            if (getInstance().getRemainingMeters() == 0) {
                var multiplier = PlayState.scoreMetersListener.getMultiplier();
                PlayState.scoreMetersListener.setMultiplier(multiplier / getInstance().getMultiplier());
                getInstance().setMultiplier(1);
            }
        }
    }
}
