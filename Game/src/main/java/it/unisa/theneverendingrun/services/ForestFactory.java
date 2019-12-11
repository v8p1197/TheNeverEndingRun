package it.unisa.theneverendingrun.services;

import com.badlogic.gdx.Gdx;
import it.unisa.theneverendingrun.models.background.AbstractScrollingBackground;
import it.unisa.theneverendingrun.models.background.ForestScrollingBackground;
import it.unisa.theneverendingrun.models.hero.ForestHero;
import it.unisa.theneverendingrun.models.hero.Hero;

public class ForestFactory implements GameFactory {

    private static final float SCROLLING_SPEED = 0.002F;
    private static final float SCROLLING_WIDTH = 2.0F;

    private Hero hero;

    public ForestFactory() {
        hero = setHero();
    }

    private Hero setHero() {
        float baseX = 0.2f * Gdx.graphics.getWidth();
        float baseY = 0.0625f * Gdx.graphics.getHeight();
        hero = new ForestHero(baseX, baseY);
        hero.flip(false, true);
        return hero;
    }

    @Override
    public Hero createHero() {
        return hero;
    }

    @Override
    public AbstractScrollingBackground createBackground() {
        return new ForestScrollingBackground(SCROLLING_SPEED, SCROLLING_WIDTH);
    }
}
