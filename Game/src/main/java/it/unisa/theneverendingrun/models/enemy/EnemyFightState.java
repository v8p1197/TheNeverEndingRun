package it.unisa.theneverendingrun.models.enemy;

public abstract class EnemyFightState {

    protected AbstractEnemy enemy;

    EnemyEventManager events;

    public EnemyFightState(AbstractEnemy enemy) {
        this.enemy = enemy;
    }

    public abstract void onIdle();

    public abstract void onAttack();

    public abstract void onDie();

}
