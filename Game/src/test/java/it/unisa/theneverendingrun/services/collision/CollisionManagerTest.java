package it.unisa.theneverendingrun.services.collision;

import de.tomgrill.gdxtesting.GdxTestRunner;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.services.factories.impls.ForestFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class CollisionManagerTest {

    private ForestFactory forestFactory;

    private int width = 1000;
    private int height = 500;
    private Hero hero;

    @Before
    public void setUp() throws Exception {

        forestFactory = new ForestFactory(width, height);
        hero = forestFactory.createHero();

    }

    @Test
    public void checkCollision() {

    }
}