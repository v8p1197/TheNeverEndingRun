package it.unisa.theneverendingrun.models.enemy.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.models.enemy.Enemy;
import it.unisa.theneverendingrun.models.enemy.EnemyState;
import it.unisa.theneverendingrun.models.enemy.EnemyStateType;

import java.util.Map;

public class EnemyDeadState extends EnemyState {


    /**
     * @param enemy the enemy that has the state
     */
    public EnemyDeadState(Enemy enemy, Map<EnemyStateType, Animation<TextureRegion>> animations) {
        super(enemy, animations);
    }

    /**
     *
     * From dead state to idle do nothing
     */
    @Override
    public void onIdle() {}

    /**
     *
     * From dead state to attack state do nothing
     */
    @Override
    public void onAttack() {}


    /**
     *
     * From dead state to dead state do nothing
     */
    @Override
    public void onDie() {
    }

    /**
     *
     * @see EnemyStateType
     * @return the current enemy animation type based on the current state
     */
    @Override
    protected EnemyStateType getEnemyType() {
        return EnemyStateType.DEAD;
    }
}
