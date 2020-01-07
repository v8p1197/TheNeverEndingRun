package it.unisa.theneverendingrun.models;

import com.badlogic.gdx.graphics.Texture;

import java.util.concurrent.ThreadLocalRandom;

public class JumpableSprite extends Sprite {


    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */


    /**
     *
     * @see Sprite#Sprite()
     *
     * Create a {@link JumpableSprite}.
     * At constructor time the sprite height is less than the maxHeight
     *
     * @param maxHeight the max height that the sprite can have
     * @param resize    if the sprite has to be resized.
     *                  If is false and getHeight() >= maxHeight return true or getHeight() == 0,
     *                  then {@link JumpableSprite#resize(float)} is called
     */
    public JumpableSprite(float maxHeight, boolean resize) {
        this(1, maxHeight, resize);
    }

    /**
     *
     * @see Sprite#Sprite()
     *
     * Create a {@link JumpableSprite}.
     * At constructor time the sprite height is less than the maxHeight
     *
     * @param maxHeight the max height that the sprite can have
     * @param resize    if the sprite has to be resized.
     *                  If is false and getHeight() >= maxHeight return true or getHeight() == 0,
     *                  then {@link JumpableSprite#resize(float)} is called
     */
    public JumpableSprite(float scaleFactor, float maxHeight, boolean resize) {
        super(scaleFactor);

        if (resize || getHeight() >= maxHeight || getHeight() == 0)
            resize(maxHeight);
    }

    /**
     *
     * @see Sprite#Sprite()
     *
     * Create a {@link JumpableSprite}.
     * At constructor time the sprite height is less than the maxHeight
     *
     * @param maxHeight the max height that the sprite can have
     * @param resize    if the sprite has to be resized.
     *                  If is false and getHeight() >= maxHeight return true or getHeight() == 0,
     *                  then {@link JumpableSprite#resize(float)} is called
     */
    public JumpableSprite(Texture texture, float maxHeight, boolean resize) {
        this(texture, 1, maxHeight, resize);
    }

    /**
     *
     * @see Sprite#Sprite()
     *
     * Create a {@link JumpableSprite}.
     * At constructor time the sprite height is less than the maxHeight
     *
     * @param maxHeight the max height that the sprite can have
     * @param resize    if the sprite has to be resized.
     *                  If is false and getHeight() >= maxHeight return true or getHeight() == 0,
     *                  then {@link JumpableSprite#resize(float)} is called
     */
    public JumpableSprite(Texture texture, float scaleFactor, float maxHeight, boolean resize) {
        super(texture, scaleFactor);

        if (resize || getHeight() >= maxHeight || getHeight() == 0)
            resize(maxHeight);
    }


    /**
     *
     * Create a {@link JumpableSprite} starting from another {@link Sprite}
     * and resize it so that is jumpable
     *
     * @param sprite the sprite that has to be jumpable
     * @param maxHeight the max height that the sprite can have
     * @param resize    if the sprite has to be resized.
     *                  If is false and getHeight() >= maxHeight return true or getHeight() == 0,
     *                  then {@link JumpableSprite#resize(float)} is called
     */
    public JumpableSprite(Sprite sprite, float maxHeight, boolean resize) {
        super(sprite.getScaleFactor());

        set(sprite);

        if (resize || getHeight() >= maxHeight || getHeight() == 0)
            resize(maxHeight);

    }




    /* -------------------------------- SERVICE METHODS -------------------------------- */

    /**
     *
     * Resize sprite in random mode so that his height is less than maxHeight
     */
    private void resize(float maxHeight) {
        var minH = Math.min(getHeight(), maxHeight * 0.5 - 1);
        var maxH = maxHeight - 1;

        float newH = (float) ThreadLocalRandom.current().nextDouble(minH, maxH);
        setSize(getWidth(), newH);
    }


    // TODO delete this method
    @Override
    public SpriteImplType getSpriteImplType() {
        return null;
    }
}
