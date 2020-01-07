package it.unisa.theneverendingrun.models.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.models.Animatable;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.Visitor;
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
     * @param animations the animations of the enemy for state
     * @see Sprite#Sprite()
     * <p>
     * Create an {@link Enemy} and pass animations to {@link EnemyState} so that when state change the animation change
     */
    public Enemy(Map<EnemyStateType, Animation<TextureRegion>> animations) {
        this(1, animations);
    }

    /**
     * @param animations the animations of the enemy for state
     * @see Sprite#Sprite(float)
     * <p>
     * Create an {@link Enemy} and pass animations to {@link EnemyState} so that when state change the animation change
     */
    public Enemy(float scaleFactor, Map<EnemyStateType, Animation<TextureRegion>> animations) {
        super(scaleFactor);
        changeEnemyState(new EnemyIdleState(this, animations));
        animate();
    }

    /**
     * @param animations the animations of the enemy for state
     * @see Sprite#Sprite(float)
     * <p>
     * Create an {@link Enemy} and pass animations to {@link EnemyState} so that when state change the animation change
     */
    public Enemy(Texture texture, Map<EnemyStateType, Animation<TextureRegion>> animations) {
        this(texture, 1, animations);
    }

    /**
     * @param animations the animations of the enemy for state
     * @see Sprite#Sprite(float)
     * <p>
     * Create an {@link Enemy} and pass animations to {@link EnemyState} so that when state change the animation change
     */
    public Enemy(Texture texture, float scaleFactor, Map<EnemyStateType, Animation<TextureRegion>> animations) {
        super(texture, scaleFactor);
        changeEnemyState(new EnemyIdleState(this, animations));
        animate();
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


    /* ------------------------------------- VISITOR ------------------------------------- */

    /**
     * @param visitor the action to perform
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visitEnemy(this);
    }

}
