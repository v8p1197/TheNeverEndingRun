package it.unisa.theneverendingrun.models.spawnables.enemy.state;

import it.unisa.theneverendingrun.models.spawnables.enemy.AbstractEnemy;

public class EnemyAttackState extends EnemyState {

    /**
     * @param enemy the enemy that has the state
     */
    public EnemyAttackState(AbstractEnemy enemy) {
        super(enemy);
    }

    /**
     *
     * From attack state to idle change state to idle
     */
    @Override
    public void onIdle() {
        enemy.changeEnemyState(new EnemyIdleState(enemy));
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
        enemy.changeEnemyState(new EnemyDeadState(enemy));
    }
}
