package it.unisa.theneverendingrun.models.enemy.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.models.enemy.EnemyState;
import it.unisa.theneverendingrun.models.enemy.EnemyStateType;
import it.unisa.theneverendingrun.models.enemy.Enemy;

import java.util.Map;

public class EnemyAttackState extends EnemyState {

    /**
     * @param enemy the enemy that has the state
     */
    public EnemyAttackState(Enemy enemy, Map<EnemyStateType, Animation<TextureRegion>> animations) {
        super(enemy, animations);
    }

    /**
     *
     * From attack state to idle change state to idle
     */
    @Override
    public void onIdle() {
        enemy.changeEnemyState(new EnemyIdleState(enemy, animations));
    }

    /**
     *
     * From attack state to attack state do nothing
     */
    @Override
    public void onAttack() {}

    /**
     *
     * From attack state to dead state change state to dead
     */
    @Override
    public void onDie() {
        enemy.changeEnemyState(new EnemyDeadState(enemy, animations));
    }

    /**
     *
     * @see EnemyStateType
     * @return the current enemy animation type based on the current state
     */
    @Override
    protected EnemyStateType getEnemyType() {
        return EnemyStateType.ATTACK;
    }


}
