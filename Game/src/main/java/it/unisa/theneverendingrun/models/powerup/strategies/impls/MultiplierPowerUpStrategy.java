package it.unisa.theneverendingrun.models.powerup.strategies.impls;

import com.google.common.util.concurrent.FutureCallback;
import it.unisa.theneverendingrun.engine.state.PlayState;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.powerup.AbstractPowerUp;
import it.unisa.theneverendingrun.models.powerup.impls.MultiplierPowerUp;
import it.unisa.theneverendingrun.models.powerup.strategies.PowerUpStrategy;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class MultiplierPowerUpStrategy extends PowerUpStrategy {

    public MultiplierPowerUpStrategy(Hero hero, AbstractPowerUp abstractPowerUp) {
        super(hero, abstractPowerUp);
    }

    @Override
    public boolean has() {
        return false;
    }

    @Override
    public boolean collect() {
        var mVal = ((MultiplierPowerUp)abstractPowerUp).getValue();

        var a = PlayState.scoreMetersListener.getMultiplier();
        PlayState.scoreMetersListener.setMultiplier(a * mVal);


        var executor = Executors.newSingleThreadScheduledExecutor();
        Runnable task = () -> PlayState.scoreMetersListener.setMultiplier(PlayState.scoreMetersListener.getMultiplier() / mVal);
        executor.schedule(task, 7, TimeUnit.SECONDS);

        executor.shutdown();

        return true;
    }

    @Override
    public boolean consume() {
        return false;
    }
}
