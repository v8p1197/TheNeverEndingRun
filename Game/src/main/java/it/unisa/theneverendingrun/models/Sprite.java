package it.unisa.theneverendingrun.models;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.TextureRegion;

import java.security.InvalidParameterException;

public class Sprite extends org.mini2Dx.core.graphics.Sprite {



    /* ------------------------------------- PARAMS ------------------------------------- */

    /**
     *
     * Store the collision box of the sprite
     */
    private CollisionBox collisionBox;

    /**
     *
     * Store the scale factor of the sprite
     */
    private final float scaleFactor;




    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     *
     * Sprite constructor. It will call the super(), set scaleFactor to 1 (no scale) and fake collision box (0,0).
     *
     */
    public Sprite() {
        this(1);
    }

    /**
     *
     * Sprite constructor. It will call the super(), set scaleFactor and fake collision box (0,0).
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
     * Sprite constructor. It will call super, set the scale factor to 1 (no scale) and generate an initial collisionBox
     *
     */
    public Sprite(Texture texture) {
        this(texture, 0, 0, texture.getWidth(), texture.getHeight(),1);
    }

    /**
     *
     * Sprite constructor. It will call super, set the scale factor and generate an initial collisionBox
     *
     * @param scaleFactor Scale factor for the sprite
     */
    public Sprite(Texture texture, float scaleFactor) {
        this(texture, 0, 0, texture.getWidth(), texture.getHeight(), scaleFactor);
    }

    /**
     *
     * Sprite constructor. It will call super, set the scale factor to 1 (no scale) and generate an initial collisionBox
     *
     */
    public Sprite(Texture texture, int srcWidth, int srcHeight) {
        this(texture, 0,0, srcWidth, srcHeight, 1);
    }

    /**
     *
     * Sprite constructor. It will call super, set the scale factor and generate an initial collisionBox

     * @param scaleFactor Scale factor for the sprite
     */
    public Sprite(Texture texture, int srcWidth, int srcHeight, float scaleFactor) {
        this(texture, 0,0, srcWidth, srcHeight, scaleFactor);
    }

    /**
     *
     * Sprite constructor. It will call super, set the scale factor to 1 (no scale) and generate an initial collisionBox
     *
     */
    public Sprite(Texture texture, int srcX, int srcY, int srcWidth, int srcHeight) {
        this(texture, srcX, srcY, srcWidth, srcHeight, 1);
    }

    /**
     *
     * Sprite constructor. It will call super, set the scale factor and generate an initial collisionBox
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

