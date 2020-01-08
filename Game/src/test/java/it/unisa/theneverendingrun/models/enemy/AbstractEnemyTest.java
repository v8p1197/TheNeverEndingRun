package it.unisa.theneverendingrun.models.enemy;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.tomgrill.gdxtesting.GdxTestRunner;
import it.unisa.theneverendingrun.models.enemy.state.EnemyAttackState;
import it.unisa.theneverendingrun.models.enemy.state.EnemyDeadState;
import it.unisa.theneverendingrun.models.enemy.state.EnemyIdleState;
import it.unisa.theneverendingrun.utilities.TextureUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class AbstractEnemyTest {

    private Enemy golem = new TestGolem(100, 100);
    private Enemy wolf = new TestWolf(100, 100);
    private Enemy witch = new TestWitch(100, 100);

    @Test
    public void testGolemInitialise() {

        assertTrue(golem.getState() instanceof EnemyIdleState);
        assertFalse(golem.getState() instanceof EnemyAttackState);
        assertFalse(golem.getState() instanceof EnemyDeadState);
        assertFalse(golem.getPreviousState() instanceof EnemyAttackState);
    }

    @Test
    public void testGolemIdle() {

        golem.getState().onIdle();
        assertTrue(golem.getState() instanceof EnemyIdleState);
        assertTrue(golem.getAnimation().equals(TestGolem.ENEMY_ANIMATIONS.get(EnemyStateType.IDLE)));
    }

    @Test
    public void testGolemAttack() {

        golem.getState().onAttack();
        assertTrue(golem.getState() instanceof EnemyAttackState);
        assertTrue(golem.getAnimation().equals(TestGolem.ENEMY_ANIMATIONS.get(EnemyStateType.ATTACK)));
    }

    @Test
    public void testGolemDie() {

        golem.getState().onDie();
        assertTrue(golem.getState() instanceof EnemyDeadState);
        assertTrue(golem.getAnimation().equals(TestGolem.ENEMY_ANIMATIONS.get(EnemyStateType.DEAD)));
    }

    @Test
    public void testWolfInitialise() {

        assertTrue(wolf.getState() instanceof EnemyIdleState);
        assertFalse(wolf.getState() instanceof EnemyAttackState);
        assertFalse(wolf.getState() instanceof EnemyDeadState);
        assertFalse(wolf.getPreviousState() instanceof EnemyAttackState);
    }

    @Test
    public void testWolfIdle() {

        wolf.getState().onIdle();
        assertTrue(wolf.getState() instanceof EnemyIdleState);
        assertTrue(wolf.getAnimation().equals(TestWolf.ENEMY_ANIMATIONS.get(EnemyStateType.IDLE)));
    }

    @Test
    public void testWolfAttack() {

        wolf.getState().onAttack();
        assertTrue(wolf.getState() instanceof EnemyAttackState);
        assertTrue(wolf.getAnimation().equals(TestWolf.ENEMY_ANIMATIONS.get(EnemyStateType.ATTACK)));
    }

    @Test
    public void testWolfDie() {

        wolf.getState().onDie();
        assertTrue(wolf.getState() instanceof EnemyDeadState);
        assertTrue(wolf.getAnimation().equals(TestWolf.ENEMY_ANIMATIONS.get(EnemyStateType.DEAD)));
    }

    @Test
    public void testWitchInitialise() {

        assertTrue(witch.getState() instanceof EnemyIdleState);
        assertFalse(witch.getState() instanceof EnemyAttackState);
        assertFalse(witch.getState() instanceof EnemyDeadState);
        assertFalse(witch.getPreviousState() instanceof EnemyAttackState);
    }

    @Test
    public void testWitchIdle() {

        witch.getState().onIdle();
        assertTrue(witch.getState() instanceof EnemyIdleState);
        assertTrue(witch.getAnimation().equals(TestWitch.ENEMY_ANIMATIONS.get(EnemyStateType.IDLE)));
    }

    @Test
    public void testWitchAttack() {

        witch.getState().onAttack();
        assertTrue(witch.getState() instanceof EnemyAttackState);
        assertTrue(witch.getAnimation().equals(TestWitch.ENEMY_ANIMATIONS.get(EnemyStateType.ATTACK)));
    }

    @Test
    public void testWitchDie() {

        witch.getState().onDie();
        assertTrue(witch.getState() instanceof EnemyDeadState);
        assertTrue(witch.getAnimation().equals(TestWitch.ENEMY_ANIMATIONS.get(EnemyStateType.DEAD)));
    }

    private static class TestGolem extends Enemy {

        private final static float SCALE_FACTOR = 3.0f;

        private static final Map<EnemyStateType, Animation<TextureRegion>> ENEMY_ANIMATIONS;

        static {
            final String ENEMY_FRAME_PATH = "images/forest/enemies/golem/"; //text done on one enemy, others are redundant

            final var DEATH_FRAME_COUNT = 13;
            final var IDLE_FRAME_COUNT = 13;
            final var ATTACK_FRAME_COUNT = 13;

            final var DEATH_FRAMES = TextureUtils.toVector(ENEMY_FRAME_PATH + "golem_death_", "png", DEATH_FRAME_COUNT);
            final var IDLE_FRAMES = TextureUtils.toVector(ENEMY_FRAME_PATH + "golem_idle_", "png", IDLE_FRAME_COUNT);
            final var ATTACK_FRAMES = TextureUtils.toVector(ENEMY_FRAME_PATH + "golem_attack_", "png", ATTACK_FRAME_COUNT);

            ENEMY_ANIMATIONS = new HashMap<>();
            ENEMY_ANIMATIONS.put(EnemyStateType.DEAD, new Animation<>(2F, DEATH_FRAMES));
            ENEMY_ANIMATIONS.put(EnemyStateType.IDLE, new Animation<>(2F, IDLE_FRAMES));
            ENEMY_ANIMATIONS.put(EnemyStateType.ATTACK, new Animation<>(2F, ATTACK_FRAMES));
        }

        public TestGolem(float x, float y) {
            super(SCALE_FACTOR, ENEMY_ANIMATIONS);
        }
    }

    private static class TestWolf extends Enemy {

        private final static float SCALE_FACTOR = 3.0f;

        private static final Map<EnemyStateType, Animation<TextureRegion>> ENEMY_ANIMATIONS;

        static {
            final String ENEMY_FRAME_PATH = "images/forest/enemies/wolf/";

            final var DEATH_FRAME_COUNT = 13;
            final var IDLE_FRAME_COUNT = 13;
            final var ATTACK_FRAME_COUNT = 13;

            final var DEATH_FRAMES = TextureUtils.toVector(ENEMY_FRAME_PATH + "wolf_death_", "png", DEATH_FRAME_COUNT);
            final var IDLE_FRAMES = TextureUtils.toVector(ENEMY_FRAME_PATH + "wolf_idle_", "png", IDLE_FRAME_COUNT);
            final var ATTACK_FRAMES = TextureUtils.toVector(ENEMY_FRAME_PATH + "wolf_attack_", "png", ATTACK_FRAME_COUNT);

            ENEMY_ANIMATIONS = new HashMap<>();
            ENEMY_ANIMATIONS.put(EnemyStateType.DEAD, new Animation<>(2F, DEATH_FRAMES));
            ENEMY_ANIMATIONS.put(EnemyStateType.IDLE, new Animation<>(2F, IDLE_FRAMES));
            ENEMY_ANIMATIONS.put(EnemyStateType.ATTACK, new Animation<>(2F, ATTACK_FRAMES));
        }

        public TestWolf(float x, float y) {
            super(SCALE_FACTOR, ENEMY_ANIMATIONS);
        }
    }

    private static class TestWitch extends Enemy {

        private final static float SCALE_FACTOR = 3.0f;

        private static final Map<EnemyStateType, Animation<TextureRegion>> ENEMY_ANIMATIONS;

        static {
            final String ENEMY_FRAME_PATH = "images/forest/enemies/witch/";

            final var DEATH_FRAME_COUNT = 13;
            final var IDLE_FRAME_COUNT = 13;
            final var ATTACK_FRAME_COUNT = 13;

            final var DEATH_FRAMES = TextureUtils.toVector(ENEMY_FRAME_PATH + "witch_death_", "png", DEATH_FRAME_COUNT);
            final var IDLE_FRAMES = TextureUtils.toVector(ENEMY_FRAME_PATH + "witch_idle_", "png", IDLE_FRAME_COUNT);
            final var ATTACK_FRAMES = TextureUtils.toVector(ENEMY_FRAME_PATH + "witch_attack_", "png", ATTACK_FRAME_COUNT);

            ENEMY_ANIMATIONS = new HashMap<>();
            ENEMY_ANIMATIONS.put(EnemyStateType.DEAD, new Animation<>(2F, DEATH_FRAMES));
            ENEMY_ANIMATIONS.put(EnemyStateType.IDLE, new Animation<>(2F, IDLE_FRAMES));
            ENEMY_ANIMATIONS.put(EnemyStateType.ATTACK, new Animation<>(2F, ATTACK_FRAMES));
        }

        public TestWitch(float x, float y) {
            super(SCALE_FACTOR, ENEMY_ANIMATIONS);
        }
    }
}
