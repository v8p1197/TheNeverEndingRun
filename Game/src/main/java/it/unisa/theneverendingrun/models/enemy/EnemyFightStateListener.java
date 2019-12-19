package it.unisa.theneverendingrun.models.enemy;

public interface EnemyFightStateListener {

    void update(EnemyEventType eventType, AbstractEnemy enemy);
}
