package it.unisa.theneverendingrun.models.enemy;

public class EnemyDeadState extends EnemyFightState {

    public EnemyDeadState(AbstractEnemy enemy) {
        super(enemy);
    }

    @Override
    public void onIdle() {
    }

    @Override
    public void onAttack() {
    }

    @Override
    public void onDie() {
    }
}
