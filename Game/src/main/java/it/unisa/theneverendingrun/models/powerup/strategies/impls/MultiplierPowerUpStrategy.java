package it.unisa.theneverendingrun.models.powerup.strategies.impls;

import it.unisa.theneverendingrun.engine.state.PlayState;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.powerup.strategies.PowerUpStrategy;

import java.util.Timer;
import java.util.TimerTask;

public class MultiplierPowerUpStrategy extends PowerUpStrategy {

    public MultiplierPowerUpStrategy(Hero hero) {
        super(hero);
    }

    @Override
    public boolean has() {
        return false;
    }

    @Override
    public boolean collect() {
        PlayState.scoreMetersListener.setMultiplier(PlayState.scoreMetersListener.getMultiplier() * 2F);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                PlayState.scoreMetersListener.setMultiplier(PlayState.scoreMetersListener.getMultiplier());
            }
        }, (long) 7.5);

        return true;
    }

    @Override
    public boolean consume() {
        return false;
    }
}
