package it.unisa.theneverendingrun.models.enemy.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.models.enemy.EnemyAnimationType;
import it.unisa.theneverendingrun.models.enemy.AbstractEnemy;

import java.util.Map;

public class EnemyIdleState extends EnemyState {


    /**
     * @param enemy the enemy that has the state
     */
    public EnemyIdleState(AbstractEnemy enemy, Map<EnemyAnimationType, Animation<TextureRegion>> animations) {
        super(enemy, animations);
    }

    /**
     *
     * From idle state to idle do nothing
     */
    @Override
    public void onIdle() {
    }

    /**
     *
     * From idle state to attack state change state to attack
     */
    @Override
    public void onAttack() {
        enemy.changeEnemyState(new EnemyAttackState(enemy, animations));
    }


    /**
     *
     * From idle state to dead state change state to dead
     */
    @Override
    public void onDie() {
        enemy.changeEnemyState(new EnemyDeadState(enemy, animations));
    }

    /**
     *
     * @see EnemyAnimationType
     * @return the current enemy animation type based on the current state
     */
    @Override
    protected EnemyAnimationType computeAnimationType() {
        return EnemyAnimationType.IDLE;
    }

}
