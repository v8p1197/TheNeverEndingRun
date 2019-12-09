package it.unisa.theneverendingrun.models.hero;

import com.badlogic.gdx.graphics.Texture;
import de.tomgrill.gdxtesting.GdxTestRunner;
import it.unisa.theneverendingrun.utilities.MathUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class HeroTest {

    private static Texture texture = new Texture("images/canepazzo.png");
    private Hero hero = new TestHero(100, 100);

    private static void assertEqualsDouble(double expected, double actual) {
        assertEquals(expected, actual, MathUtils.DELTA);
    }

    @Test
    public void testCreation() {
        assertTrue(hero.isRight());
        assertFalse(hero.isRunning());
        assertFalse(hero.isJumping());
        assertFalse(hero.isSliding());
        assertFalse(hero.isFalling());
        assertTrue(hero.getMoveState() instanceof IdleState);
    }

    @Test
    public void testLeft() {
        var initialX = hero.getX();
        var initialY = hero.getY();

        hero.getFacingState().onLeft();
        assertTrue(hero.isLeft());

        hero.setDx(2);
        hero.move();

        assertEqualsDouble(initialX - hero.getDx(), hero.getX());
        assertEqualsDouble(initialY, hero.getY());
    }

    @Test
    public void testRight() {
        var initialX = hero.getX();
        var initialY = hero.getY();

        hero.getFacingState().onRight();
        assertTrue(hero.isRight());

        hero.setDx(2);
        hero.move();

        assertEqualsDouble(initialX + hero.getDx(), hero.getX());
        assertEqualsDouble(initialY, hero.getY());
    }

    @Test
    public void testJump() {
        var initialX = hero.getX();
        var initialY = hero.getY();

        hero.getMoveState().onJump();
        assertTrue(hero.isJumping());

        while (true) {
            var previousY = hero.getY();
            var i = hero.getJumpCount();

            hero.move();
            if (!hero.isJumping()) break;

            var up = i < 0 ? -1 : 1;
            var expectedY = previousY + (i*i) * hero.getJumpCoefficient() * up;

            if (i == 0) assertEqualsDouble(hero.getJumpMaxElevation(), (hero.getY() - initialY));

            assertEqualsDouble(expectedY, hero.getY());
        }

        assertTrue(hero.getMoveState() instanceof IdleState);
        assertEqualsDouble(initialX, hero.getX());
        assertEqualsDouble(initialY, hero.getY());
    }

    @Test
    public void testJumpLeft() {
        var initialX = hero.getX();
        var speed = new Random().nextInt(10);

        hero.getFacingState().onLeft();
        hero.setDx(speed);
        hero.getMoveState().onJump();

        while (hero.isJumping())
            hero.move();

        assertEqualsDouble(initialX - hero.getMaxJumpRange()*speed - speed, hero.getX());
    }

    @Test
    public void testJumpRight() {
        var initialX = hero.getX();
        var speed = new Random().nextInt(10);

        hero.setDx(speed);
        hero.getMoveState().onJump();

        while (hero.isJumping())
            hero.move();

        assertEqualsDouble(initialX + hero.getMaxJumpRange()*speed + speed, hero.getX());
    }

    @Test
    public void testSlide() {
        var initialX = hero.getX();
        var initialY = hero.getY();

        hero.getMoveState().onSlide();
        assertTrue(hero.isSliding());

        while (hero.isSliding()) {
            hero.move();

            assertEqualsDouble(initialX, hero.getX());
            assertEqualsDouble(initialY, hero.getY());
        }

        assertEqualsDouble(initialX, hero.getX());
        assertEqualsDouble(initialY, hero.getY());
    }

    @Test
    public void testSlideLeft() {
        var initialX = hero.getX();
        var speed = new Random().nextInt(10);

        hero.getFacingState().onLeft();
        hero.setDx(speed);
        hero.getMoveState().onSlide();

        while (hero.isSliding())
            hero.move();

        assertEqualsDouble(initialX - hero.getMaxSlideRange()*speed - speed, hero.getX());
    }

    @Test
    public void testSlideRight() {
        var initialX = hero.getX();
        var speed = new Random().nextInt(10);

        hero.setDx(speed);
        hero.getMoveState().onSlide();

        while (hero.isSliding())
            hero.move();

        assertEqualsDouble(initialX + hero.getMaxSlideRange()*speed + speed, hero.getX());
    }

    @Test
    public void testStopJump() {
        var initialY = hero.getY();

        // the hero can jump on an obstacle only on the descending branch of the parabola
        var stop = -(new Random().nextInt(hero.getJumpDuration()));

        hero.getMoveState().onJump();
        assertTrue(hero.isJumping());

        while (hero.isJumping()) {
            // Simulating the hero jumps on an obstacle
            if (hero.getJumpCount() == stop)
                hero.getMoveState().onIdle();

            hero.move();
        }

        assertTrue(hero.getMoveState() instanceof IdleState);
        assertNotEquals(initialY, hero.getY());
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
            assertNotEquals(initialY, hero.getY());
            hero.move();
        }

        assertTrue(hero.getMoveState() instanceof IdleState);
        assertEqualsDouble(initialY, hero.getY());
    }

    private static class TestHero extends Hero {
        public TestHero(float x, float y) {
            super(texture, x, y);
        }
    }
}