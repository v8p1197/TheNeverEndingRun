package it.unisa.theneverendingrun.models.spawnables.enemy.state;

import it.unisa.theneverendingrun.models.spawnables.enemy.Enemy;

public class EnemyDeadState extends EnemyState {


    /**
     * @param enemy the enemy that has the state
     */
    public EnemyDeadState(Enemy enemy) {
        super(enemy);
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
}
