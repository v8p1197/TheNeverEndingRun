package it.unisa.theneverendingrun.models.spawnables.enemy.state;

import it.unisa.theneverendingrun.models.spawnables.enemy.Enemy;

public abstract class EnemyState {

    /**
     *
     * The enemy that has the state
     */
    protected Enemy enemy;

    /**
     *
     * @param enemy the enemy that has the state
     */
    public EnemyState(Enemy enemy) {
        this.enemy = enemy;
    }

    /**
     *
     * What the enemy has to do when idle state is called
     */
    public abstract void onIdle();

    /**
     *
     * What the enemy has to do when attack state is called
     */
    public abstract void onAttack();

    /**
     *
     * What the enemy has to do when death state is called
     */
    public abstract void onDie();

}
