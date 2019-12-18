package it.unisa.theneverendingrun.services;

import com.badlogic.gdx.Gdx;
import it.unisa.theneverendingrun.models.Spawnable;
import it.unisa.theneverendingrun.models.background.AbstractScrollingBackground;
import it.unisa.theneverendingrun.models.background.ForestScrollingBackground;
import it.unisa.theneverendingrun.models.enemy.Wolf;
import it.unisa.theneverendingrun.models.hero.ForestHero;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.obstacles.JumpableObstacle;
import it.unisa.theneverendingrun.models.obstacles.JumpableSlidableObstacle;
import it.unisa.theneverendingrun.models.obstacles.SlidableObstacle;

public class ForestFactory implements GameFactory {

    private static final float SCROLLING_SPEED = 0.002F;
    private static final float SCROLLING_WIDTH = 2.0F;

    private Hero hero;

    public ForestFactory() {
        hero = setHero();
    }

    @Override
    public AbstractScrollingBackground createBackground() {
        return new ForestScrollingBackground(SCROLLING_SPEED, SCROLLING_WIDTH);
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

    public Wolf createWolf() {
        return new Wolf(hero.getWidth() * 1.5f, hero.getHeight() * 0.8f);
    }

    public JumpableObstacle createJumpableObstacle() {
        return new JumpableObstacle((float) hero.getJumpMaxElevation(), hero.getWidth());
    }

    public SlidableObstacle createSlidableObstacle() {
        return new SlidableObstacle((float) hero.getMaxSlideRange() * 3); //fixme must be function of speed
    }

    public Spawnable createJumpableSlidableObstacle() {
        return new JumpableSlidableObstacle((float) hero.getJumpMaxElevation(), (float) hero.getMaxSlideRange() * 3); //fixme idem
    }
}
