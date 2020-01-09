package it.unisa.theneverendingrun.models.hero;

import de.tomgrill.gdxtesting.GdxTestRunner;
import it.unisa.theneverendingrun.services.factories.GameFactory;
import it.unisa.theneverendingrun.services.factories.impls.ForestFactory;
import it.unisa.theneverendingrun.utilities.MathUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class AbstractHeroTest {

    private GameFactory factory = new ForestFactory();
    private Hero hero = factory.createHero();

    private static void assertEqualsDouble(double expected, double actual) {
        assertEquals(expected, actual, MathUtils.DELTA);
    }

    private static void assertNotEqualsDouble(double expected, double actual) {
        assertNotEquals(expected, actual, MathUtils.DELTA);
    }

    @Test
    public void testInitialise() {
        var initialX = hero.getX();
        var initialY = hero.getY();

        assertTrue(hero.isRight());
        assertFalse(hero.isLeft());

        assertTrue(hero.isIdle());
        assertFalse(hero.isSliding());
        assertFalse(hero.isRunning());
        assertFalse(hero.isJumping());
        assertFalse(hero.isSliding());
        assertFalse(hero.isFalling());

        assertEqualsDouble(initialX, hero.getGroundX());
        assertEqualsDouble(initialY, hero.getGroundY());
    }

    @Test
    public void testLeft() {
        var speed = new Random().nextInt(10);

        hero.getFacingState().onLeft();
        hero.getMoveState().onRun();

        assertTrue(hero.isLeft());
        assertTrue(hero.isRunning());

        assertEquals(hero.getAnimation(), factory.getHeroAnimations().get(HeroStateType.RUN));

        hero.setDx(speed);
        hero.move();

        assertEqualsDouble(hero.getGroundX() - hero.getDx(), hero.getX());
        assertEqualsDouble(hero.getGroundY(), hero.getY());
    }

    @Test
    public void testRight() {
        var speed = new Random().nextInt(10);

        hero.getFacingState().onRight();
        hero.getMoveState().onRun();

        assertTrue(hero.isRight());
        assertTrue(hero.isRunning());

        assertEquals(hero.getAnimation(), factory.getHeroAnimations().get(HeroStateType.RUN));

        hero.setDx(speed);
        hero.move();

        assertEqualsDouble(hero.getGroundX() + hero.getDx(), hero.getX());
        assertEqualsDouble(hero.getGroundY(), hero.getY());
    }

    @Test
    public void testJump() {
        hero.getMoveState().onJump();
        assertTrue(hero.isJumping());

        while (hero.isJumping()) {
            assertEquals(hero.getAnimation(), factory.getHeroAnimations().get(HeroStateType.JUMP));
            hero.move();
            assertTrue(hero.getGroundY() < hero.getY());
        }

        var top = hero.getGroundY() + hero.getJumpMaxElevation();
        assertEqualsDouble(top, hero.getY());

        assertTrue(hero.isFalling());

        while (hero.isFalling()) {
            assertEquals(hero.getAnimation(), factory.getHeroAnimations().get(HeroStateType.FALL));
            assertTrue(hero.getGroundY() < hero.getY());
            hero.move();
            assertTrue(top > hero.getY());
        }

        assertTrue(hero.isStanding());
        assertEquals(hero.getAnimation(), factory.getHeroAnimations().get(HeroStateType.STAND));

        assertEqualsDouble(hero.getGroundX(), hero.getX());
        assertEqualsDouble(hero.getGroundY(), hero.getY());
    }

    @Test
    public void testJumpLeft() {
        var speed = ThreadLocalRandom.current().nextInt(10);

        hero.getFacingState().onLeft();
        hero.getMoveState().onJump();

        hero.setDx(speed);

        while (hero.isJumping() || hero.isFalling())
            hero.move();

        assertEqualsDouble(hero.getGroundX() - hero.getMaxJumpRange() * speed, hero.getX());
        assertEqualsDouble(hero.getGroundY(), hero.getY());
        assertEquals(hero.getAnimation(), factory.getHeroAnimations().get(HeroStateType.RUN));
    }

    @Test
    public void testJumpRight() {
        var speed = ThreadLocalRandom.current().nextInt(10);

        hero.getFacingState().onRight();
        hero.getMoveState().onJump();

        hero.setDx(speed);

        while (hero.isJumping() || hero.isFalling())
            hero.move();

        assertEqualsDouble(hero.getGroundX() + hero.getMaxJumpRange() * speed, hero.getX());
        assertEqualsDouble(hero.getGroundY(), hero.getY());
        assertEquals(hero.getAnimation(), factory.getHeroAnimations().get(HeroStateType.RUN));
    }

    @Test
    public void testSlide() {
        hero.getMoveState().onSlide();
        assertTrue(hero.isSliding());

        while (hero.isSliding()) {
            assertEquals(hero.getAnimation(), factory.getHeroAnimations().get(HeroStateType.SLIDE));

            hero.move();

            assertEqualsDouble(hero.getGroundX(), hero.getX());
            assertEqualsDouble(hero.getGroundY(), hero.getY());
        }

        assertTrue(hero.isStanding());
        assertEquals(hero.getAnimation(), factory.getHeroAnimations().get(HeroStateType.STAND));

        assertEqualsDouble(hero.getGroundX(), hero.getX());
        assertEqualsDouble(hero.getGroundY(), hero.getY());
    }

    @Test
    public void testSlideLeft() {
        var speed = new Random().nextInt(10);

        hero.getFacingState().onLeft();
        hero.setDx(speed);
        hero.getMoveState().onSlide();

        while (hero.isSliding())
            hero.move();

        assertEqualsDouble(hero.getGroundX() - hero.getMaxSlideRange() * speed - speed, hero.getX());
        assertEquals(hero.getAnimation(), factory.getHeroAnimations().get(HeroStateType.RUN));
    }

    @Test
    public void testSlideRight() {
        var speed = new Random().nextInt(10);

        hero.getFacingState().onRight();
        hero.setDx(speed);
        hero.getMoveState().onSlide();

        while (hero.isSliding())
            hero.move();

        assertEqualsDouble(hero.getGroundX() + hero.getMaxSlideRange() * speed + speed, hero.getX());
        assertEquals(hero.getAnimation(), factory.getHeroAnimations().get(HeroStateType.RUN));
    }

    @Test
    public void testFall() {
        var initialY = hero.getGroundY();

        // Simulating the hero is above the ground
        hero.setY(500);

        // Simulating the hero starts falling
        hero.getMoveState().onFall();
        assertTrue(hero.isFalling());

        while (hero.isFalling()) {
            assertEquals(hero.getAnimation(), factory.getHeroAnimations().get(HeroStateType.FALL));
            assertNotEquals(initialY, hero.getY());
            hero.move();
        }

        assertTrue(hero.isIdle());
        assertEqualsDouble(hero.getGroundY(), hero.getY());
    }
}
