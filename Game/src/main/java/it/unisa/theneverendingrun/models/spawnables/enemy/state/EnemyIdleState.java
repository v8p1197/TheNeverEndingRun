package it.unisa.theneverendingrun.models.spawnables.enemy.state;

import it.unisa.theneverendingrun.models.spawnables.enemy.Enemy;

public class EnemyIdleState extends EnemyState {


    /**
     * @param enemy the enemy that has the state
     */
    public EnemyIdleState(Enemy enemy) {
        super(enemy);
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
        enemy.changeEnemyState(new EnemyAttackState(enemy));
    }


    /**
     *
     * From idle state to dead state change state to dead
     */
    @Override
    public void onDie() {
        enemy.changeEnemyState(new EnemyDeadState(enemy));
    }

}
