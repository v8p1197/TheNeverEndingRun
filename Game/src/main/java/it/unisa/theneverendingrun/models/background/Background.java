package it.unisa.theneverendingrun.models.background;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.SpriteDescriptionType;

/**
 *
 * An abstraction of the Sprite class that adds support to original texture size.
 */
public class Background extends Sprite {



    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     *
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     */
    public Background(Texture texture) {
        this(texture, 0,0, texture.getWidth(), texture.getHeight(), 1);
    }

    /**
     *
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     */
    public Background(Texture texture, float scaleFactor) {
        this(texture, 0,0, texture.getWidth(), texture.getHeight(), scaleFactor);
    }

    /**
     *
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     */
    public Background(Texture texture, int backgroundWidth, int backgroundHeight) {
        this(texture, 0,0, backgroundWidth, backgroundHeight, 1);
    }

    /**
     *
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     */
    public Background(Texture texture, int backgroundWidth, int backgroundHeight, float scaleFactor) {
        this(texture, 0,0, backgroundWidth, backgroundHeight, scaleFactor);
    }

    /**
     *
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     */
    public Background(Texture texture, int srcX, int srcY, int backgroundWidth, int backgroundHeight) {
        this(texture, srcX,srcY, backgroundWidth, backgroundHeight, 1);
    }

    /**
     *
     * @see Sprite#Sprite(Texture, int, int, int, int, float)
     */
    public Background(Texture texture, int srcX, int srcY, int backgroundWidth, int backgroundHeight, float scaleFactor) {
        super(texture, srcX, srcY, backgroundWidth, backgroundHeight, scaleFactor);
    }




    /* ------------------------------------- GETTERS ------------------------------------- */

    /**
     *
     * @return the sprite description
     */
    @Override
    public SpriteDescriptionType getSpriteType() {
        return SpriteDescriptionType.BACKGROUND;
    }

}
