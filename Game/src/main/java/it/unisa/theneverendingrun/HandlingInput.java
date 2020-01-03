package it.unisa.theneverendingrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import it.unisa.theneverendingrun.models.hero.AbstractHero;

public class HandlingInput {

    public void getKeyWASD(AbstractHero hero, float speed) {

        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            hero.getFacingState().onRight();
            hero.getMoveState().onRun();

            if (hero.getX() < hero.getGroundX())
                hero.setDx(speed * 2f);
            else
                hero.setDx(speed);

        } else
            hero.setDx(0);

        if (Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            hero.getMoveState().onJump();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            hero.getFacingState().onLeft();
            hero.getMoveState().onRun();
            hero.setDx(speed);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            hero.getMoveState().onSlide();
        }

    }
}
