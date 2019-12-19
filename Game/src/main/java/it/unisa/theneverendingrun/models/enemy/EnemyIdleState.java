package it.unisa.theneverendingrun.models.enemy;

public class EnemyIdleState extends EnemyFightState {

    public EnemyIdleState(AbstractEnemy enemy) {
        super(enemy);
    }

    @Override
    public void onIdle() {
    }

    @Override
    public void onAttack() {
        enemy.changeFightState(new EnemyAttackState(enemy));
    }

    @Override
    public void onDie() {
        enemy.changeFightState(new EnemyDeadState(enemy));
    }
}
