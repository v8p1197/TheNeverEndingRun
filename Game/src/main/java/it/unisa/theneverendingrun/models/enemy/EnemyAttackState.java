package it.unisa.theneverendingrun.models.enemy;

public class EnemyAttackState extends EnemyFightState {

    public EnemyAttackState(AbstractEnemy enemy) {
        super(enemy);
    }

    @Override
    public void onIdle() {
        var animation = enemy.getAnimator().getAnimation();

        if (animation.isAnimationFinished(enemy.getAnimator().getStateTime())) {
            enemy.changeFightState(new EnemyIdleState(enemy));
        }
    }

    @Override
    public void onAttack() {

    }

    @Override
    public void onDie() {
        enemy.changeFightState(new EnemyDeadState(enemy));
    }
}
