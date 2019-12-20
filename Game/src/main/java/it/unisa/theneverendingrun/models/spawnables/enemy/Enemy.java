package it.unisa.theneverendingrun.models.spawnables.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import it.unisa.theneverendingrun.data.AnimationTypes;
import it.unisa.theneverendingrun.models.AnimatedSprite;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.spawnables.Collidable;
import it.unisa.theneverendingrun.models.spawnables.Spawnable;
import it.unisa.theneverendingrun.models.spawnables.enemy.state.EnemyAttackState;
import it.unisa.theneverendingrun.models.spawnables.enemy.state.EnemyDeadState;
import it.unisa.theneverendingrun.models.spawnables.enemy.state.EnemyIdleState;
import it.unisa.theneverendingrun.models.spawnables.enemy.state.EnemyState;

import java.util.Map;

public abstract class Enemy extends AnimatedSprite<AnimationTypes, TextureRegion> implements Spawnable, Collidable {


    /* ------------------------------------- PARAMS ------------------------------------- */

    /**
     *
     * Current State of the enemy
     */
    private EnemyState state;

    /**
     *
     * Store the jump height of the object that need to jump over the enemy
     */
    private final float jumpHeight;

    /**
     *
     * Store the slide distance of the object that need to slide over the enemy
     */
    private final float slideDistance;



    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     *
     * Enemy constructor. Call the super and set the animations the jumpHeight and the slideDistance
     * Set the default state to idle
     *
     * @param animations the animations of the enemy
     * @param jumpHeight the jump height of the object that need to jump over the obstacle
     * @param slideDistance the slide distance of the object that need to slide over the obstacle
     */
    public Enemy(Texture texture, Map<AnimationTypes, Animation<TextureRegion>> animations, float jumpHeight, float slideDistance) {
        this(texture, 1, animations, jumpHeight, slideDistance);
    }

    /**
     *
     * Enemy constructor. Call the super and set the animations the jumpHeight and the slideDistance
     * Set the default state to idle
     *
     * @param animations the animations of the enemy
     * @param jumpHeight the jump height of the object that need to jump over the obstacle
     * @param slideDistance the slide distance of the object that need to slide over the obstacle
     */
    public Enemy(Texture texture, float scaleFactor, Map<AnimationTypes, Animation<TextureRegion>> animations, float jumpHeight, float slideDistance) {
        super(texture, scaleFactor, animations);
        this.jumpHeight = jumpHeight;
        this.slideDistance = slideDistance;
        changeEnemyState(new EnemyIdleState(this));
    }



    /* ------------------------------------- GETTERS ------------------------------------- */

    /**
     *
     * @see Enemy#jumpHeight
     * @return the jump height of the object that need to jump over the enemy
     */
    @Override
    public float getJumpHeight() {
        return this.jumpHeight;
    }

    /**
     *
     * @see Enemy#slideDistance
     * @return the slide distance of the object that need to slide over the enemy
     */
    @Override
    public float getSlideDistance() {
        return slideDistance;
    }

    /**
     *
     * @see Enemy#state
     * @return the current enemy state
     */
    public EnemyState getState() {
        return state;
    }



    /* ------------------------------------- SETTERS ------------------------------------- */

    /**
     *
     * Set the new enemy state and change the animation
     *
     * @see Enemy#state
     * @param fightState the enemy state
     */
    public void changeEnemyState(EnemyState fightState) {
        this.state = fightState;
        changeAnimation();
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
     * @return true if the enemy is dead
     */
    public boolean isDead() {
        return getState() instanceof EnemyDeadState;
    }

    /**
     *
     * @return true if the enemy is idle
     */
    public boolean isIdle() {
        return getState() instanceof EnemyIdleState;
    }

    /**
     *
     * @param hero the hero that can collide with the enemy
     * @return true if the enemy collide with the hero
     */
    @Override
    public boolean isColliding(Hero hero) {
        return getCollisionBox().intersects(hero.getCollisionBox());
    }



    /* ------------------------------------- COLLISION ------------------------------------- */

    /**
     * What the enemy have to do when the hero collide with the enemy
     *
     * @param hero the hero that collide with the enemy
     */
    @Override
    public void beginColliding(Hero hero) {
        this.getState().onAttack();
        hero.die();
    }

    /**
     * What the enemy have to do when collision with hero end
     *
     * @param hero the hero that collide with the enemy
     */
    @Override
    public void endColliding(Hero hero) {
        this.getState().onIdle();
    }



    /* ------------------------------------- SERVICE METHODS ------------------------------------- */

    /**
     * Change the animation when called.
     */
    @Override
    public void changeAnimation() {

        AnimationTypes type = null;
        if (isIdle()) {
            type = AnimationTypes.IDLE;
        }

        if (isDead()) {
            type = AnimationTypes.DEAD;
        }

        if (isAttacking()) {
            type = AnimationTypes.FIGHT;
        }

        if (type == null) return;

        var frame = animations.get(type).getKeyFrame(getStateTime(), true);
        this.setRegion(frame);
    }
}
