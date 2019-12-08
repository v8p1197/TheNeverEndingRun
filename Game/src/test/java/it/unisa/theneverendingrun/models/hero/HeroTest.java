package it.unisa.theneverendingrun.models.hero;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HeroTest {

    private Hero hero = new ForestHero(100, 100);
    private static final double delta = 1e-6;

    private static void assertEqualsDouble(double expected, double actual) {
        Assertions.assertEquals(expected, actual, delta);
    }

    @Test
    void testCreation() {
        Assertions.assertTrue(hero.isRight());
        Assertions.assertFalse(hero.isRunning());
        Assertions.assertFalse(hero.isJumping());
        Assertions.assertFalse(hero.isSliding());
    }

    @Test
    void testLeft() {
        var initialX = hero.getX();
        var initialY = hero.getY();

        hero.getFacingState().onLeft();
        Assertions.assertTrue(hero.isLeft());

        hero.setDx(2);
        hero.move();

        assertEqualsDouble(initialX - hero.getDx(), hero.getX());
        assertEqualsDouble(initialY, hero.getY());
    }

    @Test
    void testRight() {
        var initialX = hero.getX();
        var initialY = hero.getY();

        hero.getFacingState().onRight();
        Assertions.assertTrue(hero.isRight());

        hero.setDx(2);
        hero.move();

        assertEqualsDouble(initialX + hero.getDx(), hero.getX());
        assertEqualsDouble(initialY, hero.getY());
    }

    @Test
    void testJump() {
        var initialX = hero.getX();
        var initialY = hero.getY();

        hero.getMoveState().onJump();

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

        assertEqualsDouble(initialX, hero.getX());
        assertEqualsDouble(initialY, hero.getY());
    }

    @Test
    void testSlide() {
        var initialX = hero.getX();
        var initialY = hero.getY();

        hero.getMoveState().onSlide();

        while (true) {
            hero.move();

            if (!hero.isSliding()) break;

            assertEqualsDouble(initialX, hero.getX());
            assertEqualsDouble(initialY, hero.getY());
        }
    }
}