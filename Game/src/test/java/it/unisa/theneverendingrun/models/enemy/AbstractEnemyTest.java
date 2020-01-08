package it.unisa.theneverendingrun.models.enemy;

import com.badlogic.gdx.Gdx;
import de.tomgrill.gdxtesting.GdxTestRunner;
import it.unisa.theneverendingrun.models.enemy.state.EnemyAttackState;
import it.unisa.theneverendingrun.models.enemy.state.EnemyDeadState;
import it.unisa.theneverendingrun.models.enemy.state.EnemyIdleState;
import it.unisa.theneverendingrun.services.factories.GameFactory;
import it.unisa.theneverendingrun.services.factories.impls.ForestFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class AbstractEnemyTest {

    private GameFactory factory = new ForestFactory(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    private Enemy enemy;

    @Before
    public void init() {
        var sprite = factory.createEnemy();
        assertTrue(sprite instanceof Enemy);
        enemy = (Enemy) sprite;
    }

    @Test
    public void testInitialise() {
        assertTrue(enemy.getState() instanceof EnemyIdleState);
        assertFalse(enemy.getState() instanceof EnemyAttackState);
        assertFalse(enemy.getState() instanceof EnemyDeadState);
        assertFalse(enemy.getPreviousState() instanceof EnemyAttackState);
    }

    @Test
    public void testIdle() {
        enemy.getState().onIdle();
        assertTrue(enemy.getState() instanceof EnemyIdleState);
    }

    @Test
    public void testAttack() {
        enemy.getState().onAttack();
        assertTrue(enemy.getState() instanceof EnemyAttackState);
    }

    @Test
    public void testDie() {
        enemy.getState().onDie();
        assertTrue(enemy.getState() instanceof EnemyDeadState);
    }
}