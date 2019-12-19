package it.unisa.theneverendingrun.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Spawnable;
import it.unisa.theneverendingrun.models.background.AbstractScrollingBackground;
import it.unisa.theneverendingrun.models.background.ForestScrollingBackground;
import it.unisa.theneverendingrun.models.enemy.Enemy;
import it.unisa.theneverendingrun.models.hero.ForestHero;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.obstacles.JumpableObstacle;
import it.unisa.theneverendingrun.models.obstacles.JumpableSlidableObstacle;
import it.unisa.theneverendingrun.models.obstacles.SlidableObstacle;

public class ForestFactory implements GameFactory {

    private static final float SCROLLING_SPEED = 0.002F;
    private static final float SCROLLING_WIDTH = 2.0F;
    private final String commonPath = "images/forest/";

    private final Texture jumpableTexture;
    private final Texture slidableTexture;
    private final Texture jumpableSlidableTexture;
    private final Texture textureGolem;
    private final Texture textureWolf;

    private Hero hero;

    public ForestFactory() {
        hero = setHero();
        jumpableSlidableTexture = new Texture(commonPath + "obstacles/jumpableSlidable.png");
        slidableTexture = new Texture(commonPath + "obstacles/slidable.png");
        jumpableTexture = new Texture(commonPath + "obstacles/jumpable.png");
        textureGolem = new Texture(commonPath + "enemies/golem/golem_idle_1.png");
        textureWolf = new Texture(commonPath + "enemies/wolf/wolf_idle_1.png");
    }

    @Override
    public AbstractScrollingBackground createBackground() {
        return new ForestScrollingBackground(SCROLLING_SPEED, SCROLLING_WIDTH);
    }

    private Hero setHero() {
        float baseX = 0.3f * Gdx.graphics.getWidth();
        float baseY = 0.0625f * Gdx.graphics.getHeight();
        hero = new ForestHero(baseX, baseY);
        hero.flip(false, true);
        return hero;
    }

    @Override
    public Hero createHero() {
        return hero;
    }

    public Enemy createWolf() {
        return new Enemy(textureWolf, hero.getWidth() * 1.5f, hero.getHeight() * 0.8f);
    }

    public JumpableObstacle createJumpableObstacle() {
        return new JumpableObstacle(jumpableTexture, (float) hero.getJumpMaxElevation(), hero.getWidth());
    }

    public SlidableObstacle createSlidableObstacle() {
        return new SlidableObstacle(slidableTexture, (float) hero.getMaxSlideRange() * 3); //fixme must be function of speed
    }

    public Spawnable createJumpableSlidableObstacle() {
        return new JumpableSlidableObstacle(jumpableSlidableTexture, (float) hero.getJumpMaxElevation(), (float) hero.getMaxSlideRange() * 3); //fixme idem
    }

    public Enemy createGolem() {
        return new Enemy(textureGolem, hero.getWidth() * 1.5f, hero.getHeight() * 1.5f);
    }
}
