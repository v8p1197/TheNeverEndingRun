package it.unisa.theneverendingrun.models.enemy.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.models.enemy.EnemyAnimationType;
import it.unisa.theneverendingrun.models.enemy.AbstractEnemy;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class EnemyState {

    /**
     *
     * The enemy that has the state
     */
    protected AbstractEnemy enemy;

    /**
     *
     * Animations of the enemy
     */
    protected final Map<EnemyAnimationType, Animation<TextureRegion>> animations;

    /**
     *
     * @param enemy the enemy that has the state
     * @param animations the possible animations of the enemy
     */
    public EnemyState(AbstractEnemy enemy, Map<EnemyAnimationType, Animation<TextureRegion>> animations) {
        this.enemy = enemy;
        this.animations = animations;
        setAnimation();
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

    /**
     *
     * Change the animation of the enemy depending on the particular state of the enemy
     */
    private void setAnimation() {
        var type = computeAnimationType();
        if (type == null) return;

        var animation = animations.get(type);
        if (animation == null) return;

        if (enemy.wasAttacking() && !enemy.getAnimation().isAnimationFinished(enemy.getStateTime())) {
            CompletableFuture.runAsync(() -> {
                while (!enemy.getAnimation().isAnimationFinished(enemy.getStateTime())) ;
                enemy.setAnimation(animation);
            });

            return;
        }

        enemy.setAnimation(animation);
    }

    /**
     *
     * @see EnemyAnimationType
     * @return the current enemy animation type based on the current state
     */
    protected abstract EnemyAnimationType computeAnimationType();

}
