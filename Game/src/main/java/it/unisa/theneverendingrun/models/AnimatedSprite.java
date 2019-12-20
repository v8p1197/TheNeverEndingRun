package it.unisa.theneverendingrun.models;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.Map;

public abstract class AnimatedSprite<KEY, T> extends Sprite {


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
     * Animated Sprite constructor. It will call the super() and set the animations.
     *
     * @param animations the animations of the animated sprite
     */
    public AnimatedSprite(Map<KEY, Animation<T>> animations) {
        this(1,animations);
    }

    /**
     *
     * Animated Sprite constructor. It will call the super() and set the animations.
     *
     * @param animations the animations of the animated sprite
     */
    public AnimatedSprite(float scaleFactor, Map<KEY, Animation<T>> animations) {
        super(scaleFactor);
        if (animations == null) throw new IllegalArgumentException("animations cannot be null");
        this.animations = animations;
        resetStateTime();
    }

    /**
     *
     * Animated Sprite constructor. It will call the super() and set the animations.
     *
     * @param animations    the animations of the animated sprite
     */
    public AnimatedSprite(Texture texture, Map<KEY, Animation<T>> animations) {
        this(texture, 0, 0, texture.getWidth(), texture.getHeight(), 1, animations);
    }

    /**
     *
     * Animated Sprite constructor. It will call the super() and set the animations.
     *
     * @param animations    the animations of the animated sprite
     */
    public AnimatedSprite(Texture texture, float scaleFactor, Map<KEY, Animation<T>> animations) {
        this(texture, 0, 0, texture.getWidth(), texture.getHeight(), scaleFactor, animations);
    }

    /**
     *
     * Animated Sprite constructor. It will call the super() and set the animations.
     *
     * @param animations    the animations of the animated sprite
     */
    public AnimatedSprite(Texture texture, int srcWidth, int srcHeight, Map<KEY, Animation<T>> animations) {
        this(texture, 0,0, srcWidth, srcHeight, 1, animations);
    }

    /**
     *
     * Animated Sprite constructor. It will call the super() and set the animations.
     *
     * @param animations    the animations of the animated sprite
     */
    public AnimatedSprite(Texture texture, int srcWidth, int srcHeight, float scaleFactor, Map<KEY, Animation<T>> animations) {
        this(texture, 0,0, srcWidth, srcHeight, scaleFactor, animations);
    }

    /**
     *
     * Animated Sprite constructor. It will call the super() and set the animations.
     *
     * @param animations    the animations of the animated sprite
     */
    public AnimatedSprite(Texture texture, int srcX, int srcY, int srcWidth, int srcHeight, Map<KEY, Animation<T>> animations) {
        this(texture, srcX, srcY, srcWidth, srcHeight, 1, animations);
    }

    /**
     *
     * Animated Sprite constructor. It will call the super() and set the animations.
     *
     * @param animations    the animations of the animated sprite
     */
    public AnimatedSprite(Texture texture, int srcX, int srcY, int srcWidth, int srcHeight, float scaleFactor, Map<KEY, Animation<T>> animations) {
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
     * @see AnimatedSprite#stateTime
     * @return the current state time
     */
    public float getStateTime() {
        return this.stateTime;
    }

    /**
     *
     * @see AnimatedSprite#stateTime
     * @param stateTime the updated state time
     */
    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    /**
     *
     * @see AnimatedSprite#stateTime
     * Set the state time to 0
     */
    public void resetStateTime() {
        setStateTime(0);
    }
}
