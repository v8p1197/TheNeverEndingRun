package it.unisa.theneverendingrun.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.mini2Dx.core.engine.geom.CollisionBox;

import java.security.InvalidParameterException;

/**
 *
 * Wraps {@link org.mini2Dx.core.graphics.Sprite} to add collision, scale and animation support
 */
public abstract class Sprite extends org.mini2Dx.core.graphics.Sprite {

    /* ------------------------------------- PARAMS ------------------------------------- */

    /**
     *
     * Contains the collision box of the sprite
     * @see CollisionBox
     */
    private CollisionBox collisionBox;

    /**
     *
     * Store the scale factor of the sprite,
     * If this value is 1 no scale is applied
     */
    private float scaleFactor;

    /**
     *
     * Active animation of the sprite
     * @see Animation
     */
    private Animation<TextureRegion> animation;

    /**
     *
     * Contains the original sprite width
     */
    private float standardWidth;

    /**
     *
     * Contains the original sprite height
     */
    private float standardHeight;

    /**
     *
     * The time span between the current frame and the last frame in seconds
     */
    private float stateTime;

    /**
     *
     *
     * This parameter is true if it has to appear on the screen, false otherwise
     */
    private boolean visible;


    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     *
     * @see org.mini2Dx.core.graphics.Sprite#Sprite()
     * No scale and fake collision box is created
     */
    public Sprite() {
        this(1);
    }

    /**
     *
     * @see org.mini2Dx.core.graphics.Sprite#Sprite()
     * @see Sprite#scaleFactor
     *
     * Custom scale and fake collision box is created.
     *
     * @param scaleFactor Scale factor for the sprite
     */
    public Sprite(float scaleFactor) {
        super();
        this.scaleFactor = scaleFactor;
        this.collisionBox = new CollisionBox(0, 0, 0, 0);
        this.visible = true;
        this.standardWidth = 0;
        this.standardHeight = 0;

        resetStateTime();
    }

    /**
     *
     * @see org.mini2Dx.core.graphics.Sprite#Sprite(Texture, int, int, int, int)
     * @see Sprite#scaleFactor
     */
    public Sprite(Texture texture) {
        this(texture, 0, 0, texture.getWidth(), texture.getHeight(),1);
    }

    /**
     *
     * @see org.mini2Dx.core.graphics.Sprite#Sprite(Texture, int, int, int, int)
     * @see Sprite#scaleFactor
     *
     * @param scaleFactor Scale factor for the sprite
     */
    public Sprite(Texture texture, float scaleFactor) {
        this(texture, 0, 0, texture.getWidth(), texture.getHeight(), scaleFactor);
    }

    /**
     *
     * @see org.mini2Dx.core.graphics.Sprite#Sprite(Texture, int, int, int, int)
     * @see Sprite#scaleFactor
     */
    public Sprite(Texture texture, int srcWidth, int srcHeight) {
        this(texture, 0,0, srcWidth, srcHeight, 1);
    }

    /**
     *
     * @see org.mini2Dx.core.graphics.Sprite#Sprite(Texture, int, int, int, int)
     * @see Sprite#scaleFactor
     *
     * @param scaleFactor Scale factor for the sprite
     */
    public Sprite(Texture texture, int srcWidth, int srcHeight, float scaleFactor) {
        this(texture, 0,0, srcWidth, srcHeight, scaleFactor);
    }

    /**
     *
     * @see org.mini2Dx.core.graphics.Sprite#Sprite(Texture, int, int, int, int)
     * @see Sprite#scaleFactor
     */
    public Sprite(Texture texture, int srcX, int srcY, int srcWidth, int srcHeight) {
        this(texture, srcX, srcY, srcWidth, srcHeight, 1);
    }

    /**
     *
     * @see org.mini2Dx.core.graphics.Sprite#Sprite(Texture, int, int, int, int)
     * @see Sprite#scaleFactor
     *
     * @param scaleFactor Scale factor for the sprite
     */
    public Sprite(Texture texture, int srcX, int srcY, int srcWidth, int srcHeight, float scaleFactor) {
        super(texture, srcX, srcY, srcWidth, srcHeight);
        this.scaleFactor = scaleFactor;
        this.collisionBox = new CollisionBox(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        this.visible = true;

        scale();

        this.standardWidth = getWidth();
        this.standardHeight = getHeight();

        resetStateTime();
    }

    /* ------------------------------------- GETTERS ------------------------------------- */

    public abstract void accept(Visitor visitor);


    /* ------------------------------------- GETTERS ------------------------------------- */

    /**
     * @return true if the sprite is visible, false otherwise
     * @see Sprite#visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     *
     * @see Sprite#scaleFactor
     * @return the scale factor used
     */
    public float getScaleFactor() {
        return scaleFactor;
    }

    /**
     *
     * @return the original not scaled width
     */
    public float getNotScaledWidth() {
        return getWidth() / getScaleFactor();
    }

    /**
     *
     * @return the original not scaled height
     */
    public float getNotScaledHeight() {
        return getHeight() / getScaleFactor();
    }

    public final float getStandardWidth() {
        return standardWidth;
    }

    public final float getStandardHeight() {
        return standardHeight;
    }

    /**
     *
     * @see Sprite#collisionBox
     * @return the collision box for this sprite
     */
    public CollisionBox getCollisionBox() {
        return collisionBox;
    }

    /**
     *
     * @see Sprite#animation
     * @return the current active animation for this sprite
     */
    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    /**
     *
     * @see Sprite#stateTime
     * @return the current state time
     */
    public float getStateTime() {
        return this.stateTime;
    }





    /* ------------------------------------- SETTERS ------------------------------------- */

    /**
     *
     * @see Sprite#visible
     * @param visible if the sprite has to be visible or not
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Make this sprite a copy in every way of the specified sprite
     *
     * @param sprite the old sprite
     */
    public void set(Sprite sprite) {
        super.set(sprite);
        this.collisionBox = sprite.getCollisionBox();
        this.animation = sprite.animation;
        this.scaleFactor = sprite.scaleFactor;
        this.stateTime = sprite.getStateTime();
        this.standardHeight = sprite.getStandardHeight();
        this.standardWidth = sprite.getStandardWidth();
    }

    @Override
    public void setRegion(float u, float v, float u2, float v2) {
        super.setRegion(u, v, u2, v2);
        setSize(getTexture().getWidth(), getTexture().getHeight());
        scale();
    }

    /**
     *
     * @see Sprite#collisionBox
     * Generate the new collision box after doing the super setPosition
     */
    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        if (collisionBox != null)
            collisionBox.set(x, y);
    }

    /**
     *
     * @see Sprite#collisionBox
     * Generate the new collision box after doing the super setX
     */
    @Override
    public void setX(float x) {
        super.setX(x);
        if (collisionBox != null)
            collisionBox.set(x, getY());
    }

    /**
     *
     * @see Sprite#collisionBox
     * Generate the new collision box after doing the super setY
     */
    @Override
    public void setY(float y) {
        super.setY(y);
        if (collisionBox != null)
            collisionBox.set(getX(), y);
    }

    /**
     *
     * @see Sprite#collisionBox
     * Generate the new collision box after doing the super setSize
     */
    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        if (collisionBox != null)
            collisionBox.setSize(width, height);
    }

    protected final void setStandardWidth(float standardWidth) {
        this.standardWidth = standardWidth;
    }

    protected final void setStandardHeight(float standardHeight) {
        this.standardHeight = standardHeight;
    }

    /**
     *
     * @see Sprite#animation
     * @param animation the new animation for the sprite
     */
    public void setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
    }

    /**
     *
     * @see Sprite#stateTime
     * @param stateTime the updated state time
     */
    public void setStateTime(float stateTime) {
        this.stateTime += stateTime;
    }

    /**
     *
     * @see Sprite#stateTime
     * Set the state time to 0
     */
    public void resetStateTime() {
        setStateTime(0);
    }

    /**
     *
     * Scales the sprite using width, height and scale factor
     */
    public void scale() {
        setSize(getWidth() * getScaleFactor(), getHeight() * getScaleFactor());
    }






    /* ------------------------------------- CHECK ------------------------------------- */

    /**
     *
     * Check if the Sprite is currently visible on the x axis
     */
    public boolean isXAxisVisible() {
        return (getX() + getWidth()) > 0;
    }

    /**
     *
     * Check if the Sprite is currently visible on the y axis
     */
    public boolean isYAxisVisible() {
        return (getY() + getHeight()) > 0;
    }

    /**
     *
     * Check if the Sprite is currently visible on the x axis
     */
    public boolean isXAxisVisible(double maxXAxisValue) {
        if (maxXAxisValue < 0) throw new InvalidParameterException("maxXAxisValue cannot be less than 0");
        return (getX() + getWidth()) > 0 && (getX() - getWidth()) < maxXAxisValue;
    }

    /**
     *
     * Check if the Sprite is currently visible on the y axis
     */
    public boolean isYAxisVisible(double maxYAxisValue) {
        if (maxYAxisValue < 0) throw new InvalidParameterException("maxYAxisValue cannot be less than 0");
        return (getY() + getHeight()) > 0 && (getY() - getHeight()) < maxYAxisValue;
    }

}

