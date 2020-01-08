package it.unisa.theneverendingrun.models.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Map;

public abstract class EnemyState {

    /**
     *
     * The enemy that has the state
     */
    protected Enemy enemy;

    /**
     *
     * Animations of the enemy
     */
    protected final Map<EnemyStateType, Animation<TextureRegion>> animations;

    /**
     *
     * @param enemy the enemy that has the state
     * @param animations the possible animations of the enemy
     */
    public EnemyState(Enemy enemy, Map<EnemyStateType, Animation<TextureRegion>> animations) {
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
        var type = getEnemyType();
        if (type == null) {
            enemy.setAnimation(null);
            return;
        }

        var animation = animations.get(type);
        if (animation == null) {
            enemy.setAnimation(null);
            return;
        }

        //TODO: Check
        /*
         * The dead is to fast
         * so change animation
         */
        /*if (enemy.wasAttacking() && !enemy.getAnimation().isAnimationFinished(enemy.getStateTime())) {
            CompletableFuture.runAsync(() -> {
                while (!enemy.getAnimation().isAnimationFinished(enemy.getStateTime())) ;
                enemy.setAnimation(animation);
            });

            return;
        }*/

        enemy.setAnimation(animation);
    }

    /**
     *
     * @see EnemyStateType
     * @return the current enemy animation type based on the current state
     */
    protected abstract EnemyStateType getEnemyType();

}
