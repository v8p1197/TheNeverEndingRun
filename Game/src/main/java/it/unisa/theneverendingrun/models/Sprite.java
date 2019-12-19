package it.unisa.theneverendingrun.models;

import com.badlogic.gdx.graphics.Texture;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.TextureRegion;

import java.security.InvalidParameterException;

public class Sprite extends org.mini2Dx.core.graphics.Sprite {



    //***************************** params *****************************//

    /**
     * Store the collision box of the sprite
     */
    private CollisionBox collisionBox;

    /**
     * Store the scale factor of the sprite
     */
    private final float scaleFactor;




    //***************************** constructors *****************************//

    /**
     * Sprite constructor. It will set the texture and generate an initial collisionBox
     *
     * @param texture Texture of the component
     */
    public Sprite(Texture texture) {
        this(texture, 1);
    }

    /**
     * Sprite constructor. It will set the texture and generate an initial collisionBox
     *
     * @param texture Texture of the component
     * @param scaleFactor Scale factor for the sprite
     */
    public Sprite(Texture texture, float scaleFactor) {
        super(texture);
        this.scaleFactor = scaleFactor;

        setSize(getWidth() * getScaleFactor(), getHeight() * getScaleFactor());
        generateCollisionBox();
    }

    /**
     * Sprite constructor. It will set the texture, width and height and generate an initial collisionBox
     *
     * @param texture   Texture of the component
     * @param srcWidth  width of the sprite
     * @param srcHeight height of the sprite
     */
    public Sprite(Texture texture, int srcWidth, int srcHeight) {
        this(texture, srcWidth, srcHeight, 1);
    }

    /**
     * Sprite constructor. It will set the texture, width and height and generate an initial collisionBox

     * @param texture   Texture of the component
     * @param srcWidth  width of the sprite
     * @param srcHeight height of the sprite
     * @param scaleFactor Scale factor for the sprite
     */
    public Sprite(Texture texture, int srcWidth, int srcHeight, float scaleFactor) {
        super(texture, srcWidth, srcHeight);
        this.scaleFactor = scaleFactor;

        setSize(getWidth() * getScaleFactor(), getHeight() * getScaleFactor());
        generateCollisionBox();
    }

    /**
     * Sprite constructor. It will set the texture, coordinates x and y, width and height and generate an initial collisionBox
     *
     * @param texture   Texture of the component
     * @param srcWidth  width of the sprite
     * @param srcHeight height of the sprite
     * @param srcX      coordinate x
     * @param srcY      coordinate y
     */
    public Sprite(Texture texture, int srcX, int srcY, int srcWidth, int srcHeight) {
        this(texture, srcX, srcY, srcWidth, srcHeight, 1);
    }

    /**
     * Sprite constructor. It will set the texture, coordinates x and y, width and height and generate an initial collisionBox
     *
     * @param texture   Texture of the component
     * @param srcWidth  width of the sprite
     * @param srcHeight height of the sprite
     * @param srcX      coordinate x
     * @param srcY      coordinate y
     * @param scaleFactor Scale factor for the sprite
     */
    public Sprite(Texture texture, int srcX, int srcY, int srcWidth, int srcHeight, float scaleFactor) {
        super(texture, srcX, srcY, srcWidth, srcHeight);
        this.scaleFactor = scaleFactor;

        setSize(getWidth() * getScaleFactor(), getHeight() * getScaleFactor());
        generateCollisionBox();
    }




    //***************************** getters *****************************//

    /**
     *
     * @return the scale factor used
     */
    public float getScaleFactor() {
        return scaleFactor;
    }

    /**
     * @return the original not scaled width
     */
    public float getOriginalWidth() {
        return getWidth() / getScaleFactor();
    }

    /**
     *
     * @return the original not scaled height
     */
    public float getOriginalHeight() {
        return getHeight() / getScaleFactor();
    }

    /**
     * CollisionBox getter
     */
    public CollisionBox getCollisionBox() {
        return collisionBox;
    }





    //***************************** setters *****************************//

    /**
     * Generate the new collision box after doing the super setPosition
     */
    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        collisionBox.set(x, y);
    }

    /**
     * Generate the new collision box after doing the super setX
     */
    @Override
    public void setX(float x) {
        super.setX(x);
        collisionBox.set(x, getY());
    }

    /**
     * Generate the new collision box after doing the super setY

     */
    @Override
    public void setY(float y) {
        super.setY(y);
        collisionBox.set(getX(), y);
    }

    /**
     * Generate the new collision box after doing the super setSize
     */
    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        generateCollisionBox();
    }




    //***************************** check *****************************//

    /**
     * Check if the Sprite is currently visible on the x axis
     */
    public boolean isXAxisVisible() {
        return (getX() + getWidth()) > 0;
    }

    /**
     * Check if the Sprite is currently visible on the y axis
     */
    public boolean isYAxisVisible() {
        return (getY() + getHeight()) > 0;
    }

    /**
     * Check if the Sprite is currently visible on the x axis
     */
    public boolean isXAxisVisible(double maxXAxisValue) {
        if (maxXAxisValue < 0) throw new InvalidParameterException("maxXAxisValue cannot be less than 0");
        return (getX() + getWidth()) > 0 && (getX() - getWidth()) < maxXAxisValue;
    }

    /**
     * Check if the Sprite is currently visible on the y axis
     */
    public boolean isYAxisVisible(double maxYAxisValue) {
        if (maxYAxisValue < 0) throw new InvalidParameterException("maxYAxisValue cannot be less than 0");
        return (getY() + getHeight()) > 0 && (getY() - getHeight()) < maxYAxisValue;
    }




    //***************************** private helpers *****************************//

    /**
     * Generate a new collisionBox having the current coordinates (x,y) and the sprite width and height
     */
    private void generateCollisionBox() {
        collisionBox = new CollisionBox(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }


}

