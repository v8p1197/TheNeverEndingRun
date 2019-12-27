package it.unisa.theneverendingrun.models;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Map;

/**
 *
 * A wrapper for {@link Sprite} that add support for animation
 *
 * @param <KEY> the type of the key that represent the animation. A unique value, usually an enum.
 * @param <T> the type of the animation
 */
public abstract class AbstractAnimatedSprite<KEY, T> extends Sprite {


    /* ------------------------------------- PARAMS ------------------------------------- */

    /**
     *
     * Animations of the sprite
     */
    protected final Map<KEY, Animation<T>> animations;

    /**
     *
     * The time span between the current frame and the last frame in seconds
     */
    private float stateTime;



    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     *
     * @see Sprite#Sprite(float)
     *
     * Create a {@link Sprite} with {@link AbstractAnimatedSprite#animations}
     *
     * @see AbstractAnimatedSprite#animations
     *
     * @param animations the animations of the animated sprite
     */
    public AbstractAnimatedSprite(Map<KEY, Animation<T>> animations) {
        this(1,animations);
    }

    /**
     *
     * @see Sprite#Sprite(float)
     *
     * Create a {@link Sprite} with {@link AbstractAnimatedSprite#animations}
     *
     * @see AbstractAnimatedSprite#animations
     *
     * @param animations the animations of the animated sprite
     */
    public AbstractAnimatedSprite(float scaleFactor, Map<KEY, Animation<T>> animations) {
        super(scaleFactor);
        if (animations == null) throw new IllegalArgumentException("animations cannot be null");
        this.animations = animations;
        resetStateTime();
    }

    /**
     *
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Create a {@link Sprite} with {@link AbstractAnimatedSprite#animations}
     *
     * @see AbstractAnimatedSprite#animations
     *
     * @param animations    the animations of the animated sprite
     */
    public AbstractAnimatedSprite(Texture texture, Map<KEY, Animation<T>> animations) {
        this(texture, 0, 0, texture.getWidth(), texture.getHeight(), 1, animations);
    }

    /**
     *
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Create a {@link Sprite} with {@link AbstractAnimatedSprite#animations}
     *
     * @see AbstractAnimatedSprite#animations
     *
     * @param animations    the animations of the animated sprite
     */
    public AbstractAnimatedSprite(Texture texture, float scaleFactor, Map<KEY, Animation<T>> animations) {
        this(texture, 0, 0, texture.getWidth(), texture.getHeight(), scaleFactor, animations);
    }

    /**
     *
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Create a {@link Sprite} with {@link AbstractAnimatedSprite#animations}
     *
     * @see AbstractAnimatedSprite#animations
     *
     * @param animations    the animations of the animated sprite
     */
    public AbstractAnimatedSprite(Texture texture, int srcWidth, int srcHeight, Map<KEY, Animation<T>> animations) {
        this(texture, 0,0, srcWidth, srcHeight, 1, animations);
    }

    /**
     *
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Create a {@link Sprite} with {@link AbstractAnimatedSprite#animations}
     *
     * @see AbstractAnimatedSprite#animations
     *
     * @param animations    the animations of the animated sprite
     */
    public AbstractAnimatedSprite(Texture texture, int srcWidth, int srcHeight, float scaleFactor, Map<KEY, Animation<T>> animations) {
        this(texture, 0,0, srcWidth, srcHeight, scaleFactor, animations);
    }

    /**
     *
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Create a {@link Sprite} with {@link AbstractAnimatedSprite#animations}
     *
     * @see AbstractAnimatedSprite#animations
     *
     * @param animations    the animations of the animated sprite
     */
    public AbstractAnimatedSprite(Texture texture, int srcX, int srcY, int srcWidth, int srcHeight, Map<KEY, Animation<T>> animations) {
        this(texture, srcX, srcY, srcWidth, srcHeight, 1, animations);
    }

    /**
     *
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Create a {@link Sprite} with {@link AbstractAnimatedSprite#animations}
     *
     * @see AbstractAnimatedSprite#animations
     *
     * @param animations    the animations of the animated sprite
     */
    public AbstractAnimatedSprite(Texture texture, int srcX, int srcY, int srcWidth, int srcHeight, float scaleFactor, Map<KEY, Animation<T>> animations) {
        super(texture, srcX, srcY, srcWidth, srcHeight, scaleFactor);
        if (animations == null) throw new IllegalArgumentException("animations cannot be null");
        this.animations = animations;
        resetStateTime();
    }


    /**
     *
     * Change the animation when called.
     */
    public abstract void changeAnimation();

    /**
     *
     * @see AbstractAnimatedSprite#stateTime
     * @return the current state time
     */
    public float getStateTime() {
        return this.stateTime;
    }

    /**
     *
     * @see AbstractAnimatedSprite#stateTime
     * @param stateTime the updated state time
     */
    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    /**
     *
     * @see AbstractAnimatedSprite#stateTime
     * Set the state time to 0
     */
    public void resetStateTime() {
        setStateTime(0);
    }
}
