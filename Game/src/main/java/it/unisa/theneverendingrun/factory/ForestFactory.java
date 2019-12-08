package it.unisa.theneverendingrun.factory;

import com.badlogic.gdx.Gdx;
import it.unisa.theneverendingrun.models.hero.ForestHero;
import it.unisa.theneverendingrun.models.hero.Hero;

public class ForestFactory implements RunFactory {

    private static final float SCROLLING_TIME = 0.002F;
    private static final float SCROLLING_WIDTH = 2.0F;

    private Hero hero;

    public ForestFactory() {
        hero = setHero();
    }

    private Hero setHero() {
        var baseX = 0.2 * Gdx.graphics.getWidth();
        var baseY = 0.0625 * Gdx.graphics.getHeight();
        hero = new ForestHero(baseX, baseY);

        return hero;
    }

    @Override
    public Hero createHero() {
        return hero;
    }
}
