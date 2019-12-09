package it.unisa.theneverendingrun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import it.unisa.theneverendingrun.models.hero.Hero;

public class HandlingInput {

    public void getKeyWASD(Hero hero) {

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            hero.getFacingState().onRight();
            hero.setDx(5);
        } else
            hero.setDx(0);

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            hero.getMoveState().onJump();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            hero.getFacingState().onLeft();
            hero.setDx(5);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            hero.getMoveState().onSlide();
        }

    }
}
