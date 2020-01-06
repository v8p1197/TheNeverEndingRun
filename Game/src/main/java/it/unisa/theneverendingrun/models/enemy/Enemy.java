package it.unisa.theneverendingrun.models.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.models.Animatable;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteDescriptionType;
import it.unisa.theneverendingrun.models.enemy.state.EnemyAttackState;
import it.unisa.theneverendingrun.models.enemy.state.EnemyIdleState;

import java.util.Map;

/**
 *
 * A wrapper for {@link Sprite} that add support for {@link EnemyState} and {@link Animatable}
 */
public class Enemy extends Sprite implements Animatable {


    /* ------------------------------------- PARAMS ------------------------------------- */

    /**
     *
     * Current State of the enemy
     */
    private EnemyState state;

    /**
     *
     * Previous state of the enemy
     */
    private EnemyState prevState;





    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     *
     * Create an {@link Enemy} starting from another {@link Sprite}
     * and pass animations to {@link EnemyState} so that when state change the animation change
     *
     * @param sprite the original sprite
     * @param animations the animations of the enemy for state
     */
    public Enemy(Sprite sprite, Map<EnemyStateType, Animation<TextureRegion>> animations) {
        super(sprite.getScaleFactor());
        set(sprite);
        changeEnemyState(new EnemyIdleState(this, animations));
    }


    /* ------------------------------------- GETTER ------------------------------------- */

    /**
     *
     * @see Enemy#state
     * @return the current enemy state
     */
    public EnemyState getState() {
        return state;
    }

    /**
     *
     * @see Enemy#prevState
     * @return the current enemy state
     */
    public EnemyState getPreviousState() {
        return prevState;
    }

    @Override
    public SpriteDescriptionType getName() {
        return SpriteDescriptionType.ENEMY;
    }


    /* ------------------------------------- SETTERS ------------------------------------- */

    /**
     *
     * Set the new enemy state
     *
     * @see Enemy#state
     * @param state the enemy state
     */
    public void changeEnemyState(EnemyState state) {
        this.prevState = this.state;
        this.state = state;
    }





    /* ------------------------------------- CHECK ------------------------------------- */

    /**
     *
     * @return true if the enemy is attacking
     */
    public boolean isAttacking() {
        return getState() instanceof EnemyAttackState;
    }

    /**
     *
     * @return true if the enemy was attacking
     */
    public boolean wasAttacking() {
        return getPreviousState() instanceof EnemyAttackState;
    }




    /* ------------------------------------- CHECK ------------------------------------- */

    /**
     *
     * @see Animatable#animate()
     */
    @Override
    public void animate() {

        var animation = getAnimation();
        if (animation == null) throw new NullPointerException("animation is null");

        TextureRegion frame;
        if (isAttacking())
            frame = animation.getKeyFrame(getStateTime(), false);
        else
            frame = animation.getKeyFrame(getStateTime(), true);

        if (frame == null) throw new NullPointerException("animation frame is null");

        setRegion(frame);
    }
}
