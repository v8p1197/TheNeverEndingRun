package it.unisa.theneverendingrun.models.enemy.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.models.enemy.EnemyAnimationType;
import it.unisa.theneverendingrun.models.enemy.AbstractEnemy;

import java.util.Map;

public class EnemyDeadState extends EnemyState {


    /**
     * @param enemy the enemy that has the state
     */
    public EnemyDeadState(AbstractEnemy enemy, Map<EnemyAnimationType, Animation<TextureRegion>> animations) {
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
    public void onDie() {}

    /**
     *
     * @see EnemyAnimationType
     * @return the current enemy animation type based on the current state
     */
    @Override
    protected EnemyAnimationType computeAnimationType() {
        return EnemyAnimationType.DEAD;
    }
}
