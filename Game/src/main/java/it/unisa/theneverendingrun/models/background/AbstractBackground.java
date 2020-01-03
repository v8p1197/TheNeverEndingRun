package it.unisa.theneverendingrun.models.background;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Sprite;

/**
 *
 * An abstraction of the Sprite class that adds support to original texture size.
 */
public abstract class AbstractBackground extends Sprite {

    /* ------------------------------------- PARAMS ------------------------------------- */

    /**
     *
     * Contains the original background width
     */
    private final float textureWidth;

    /**
     *
     * Contains the original background height
     */
    private final float textureHeight;



    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     *
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link AbstractBackground#textureWidth} and the {@link AbstractBackground#textureHeight},
     *
     * @see AbstractBackground#textureWidth
     * @see AbstractBackground#textureHeight
     *
     *
     */
    public AbstractBackground(Texture texture) {
        this(texture, 0,0, texture.getWidth(), texture.getHeight(), 1);
    }

    /**
     *
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link AbstractBackground#textureWidth} and the {@link AbstractBackground#textureHeight}
     *
     * @see AbstractBackground#textureWidth
     * @see AbstractBackground#textureHeight
     */
    public AbstractBackground(Texture texture, float scaleFactor) {
        this(texture, 0,0, texture.getWidth(), texture.getHeight(), scaleFactor);
    }

    /**
     *
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link AbstractBackground#textureWidth} and the {@link AbstractBackground#textureHeight}
     *
     * @see AbstractBackground#textureWidth
     * @see AbstractBackground#textureHeight
     *
     */
    public AbstractBackground(Texture texture, int backgroundWidth, int backgroundHeight) {
        this(texture, 0,0, backgroundWidth, backgroundHeight, 1);
    }

    /**
     *
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link AbstractBackground#textureWidth} and the {@link AbstractBackground#textureHeight}
     *
     * @see AbstractBackground#textureWidth
     * @see AbstractBackground#textureHeight
     *
     */
    public AbstractBackground(Texture texture, int backgroundWidth, int backgroundHeight, float scaleFactor) {
        this(texture, 0,0, backgroundWidth, backgroundHeight, scaleFactor);
    }

    /**
     *
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link AbstractBackground#textureWidth} and the {@link AbstractBackground#textureHeight}
     *
     * @see AbstractBackground#textureWidth
     * @see AbstractBackground#textureHeight
     *
     */
    public AbstractBackground(Texture texture, int srcX, int srcY, int backgroundWidth, int backgroundHeight) {
        this(texture, srcX,srcY, backgroundWidth, backgroundHeight, 1);
    }

    /**
     *
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     *
     * Set the {@link AbstractBackground#textureWidth} and the {@link AbstractBackground#textureHeight}
     *
     * @see AbstractBackground#textureWidth
     * @see AbstractBackground#textureHeight
     **
     */
    public AbstractBackground(Texture texture, int srcX, int srcY, int backgroundWidth, int backgroundHeight, float scaleFactor) {
        super(texture, srcX, srcY, backgroundWidth, backgroundHeight, scaleFactor);
        this.textureWidth = texture.getWidth();
        this.textureHeight = texture.getHeight();
    }


    /* ------------------------------------- GETTERS ------------------------------------- */

    /**
     *
     * @see AbstractBackground#textureWidth
     * Original background width getter
     */
    public float getTextureWidth() {
        return textureWidth;
    }

    /**
     *
     * @see AbstractBackground#textureHeight
     * Original background height getter
     */
    public float getTextureHeight() {
        return textureHeight;
    }

}
