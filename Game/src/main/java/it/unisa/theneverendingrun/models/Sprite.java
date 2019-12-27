package it.unisa.theneverendingrun.models;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.TextureRegion;

import java.security.InvalidParameterException;

/**
 *
 * Wraps {@link org.mini2Dx.core.graphics.Sprite} to add collision and scale support
 */
public class Sprite extends org.mini2Dx.core.graphics.Sprite {


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
    private final float scaleFactor;




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
        collisionBox = new CollisionBox(getX(), getY(), 0,0);
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

        setSize(getWidth() * getScaleFactor(), getHeight() * getScaleFactor());
        generateCollisionBox();
    }




    /* ------------------------------------- GETTERS ------------------------------------- */

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

    /**
     *
     * @see Sprite#collisionBox
     * @return the collision box for this sprite
     */
    public CollisionBox getCollisionBox() {
        return collisionBox;
    }





    /* ------------------------------------- SETTERS ------------------------------------- */

    /**
     *
     * @see Sprite#collisionBox
     * Generate the new collision box after doing the super setPosition
     */
    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
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
        generateCollisionBox();
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




    /* ------------------------------------- SERVICE METHOD ------------------------------------- */

    /**
     *
     * Generate a new collisionBox having the current coordinates (x,y) and the sprite width and height
     */
    private void generateCollisionBox() {
        collisionBox = new CollisionBox(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }


}

