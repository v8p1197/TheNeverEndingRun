package it.unisa.theneverendingrun.services.spawn.position;

import de.tomgrill.gdxtesting.GdxTestRunner;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.services.factories.GameFactory;
import it.unisa.theneverendingrun.services.factories.impls.ForestFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)

public class SpritePositioningTest {

    private static int OFFSET_MEASURE = 100;

    // dimensions
    private final int SCREEN_WIDTH = 1000;
    private final int SCREEN_HEIGHT = 500;
    private final int CYCLES = 100000;

    private GameFactory factory;
    private SpritePositioning spritePositioning;
    private Hero hero;

    @Before
    public void init() {
        factory = new ForestFactory(SCREEN_WIDTH, SCREEN_HEIGHT);
        hero = factory.createHero();
        spritePositioning = new SpritePositioning(hero, SCREEN_WIDTH, factory);
    }


    @Test
    public void getSpriteTest() {

        int i = 0;
        while (i < CYCLES) {
            Sprite sprite = spritePositioning.getSprite();
            if (sprite == null) {
                continue;
            }
            boolean isJumpable = sprite.getY() + sprite.getHeight() <= hero.getGroundY() + hero.getJumpMaxElevation();
            boolean isSlidable = sprite.getY() >= hero.getGroundY() + hero.getSlideStandardHeight();

            assertTrue(isJumpable || isSlidable);
            sprite.setX(0);
            i++;
        }

    }
}