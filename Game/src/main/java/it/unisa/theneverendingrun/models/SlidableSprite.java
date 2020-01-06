package it.unisa.theneverendingrun.models;


import com.badlogic.gdx.graphics.Texture;

import java.util.concurrent.ThreadLocalRandom;

public class SlidableSprite extends Sprite {


    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     *
     * @see Sprite#Sprite()
     *
     * Create a {@link SlidableSprite}.
     * At constructor time the sprite height is less than the maxHeight
     *
     * @param maxWidth the max width that the sprite can have
     * @param resize   if the sprite has to be resized
     *                 is false and getHeight() >= maxHeight return true or getHeight() == 0,
     *                 then {@link SlidableSprite#resize(float)} is called
     */
    public SlidableSprite(float maxWidth, boolean resize) {
        this(1, maxWidth, resize);
    }

    /**
     *
     * @see Sprite#Sprite()
     *
     * Create a {@link SlidableSprite}.
     * At constructor time the sprite height is less than the maxHeight
     *
     * @param maxWidth the max width that the sprite can have
     * @param resize   if the sprite has to be resized
     *                 is false and getHeight() >= maxHeight return true or getHeight() == 0,
     *                 then {@link SlidableSprite#resize(float)} is called
     */
    public SlidableSprite(float scaleFactor, float maxWidth, boolean resize) {
        super(scaleFactor);

        if (resize || getWidth() >= maxWidth || getWidth() == 0)
            resize(maxWidth);
    }

    /**
     *
     * @see Sprite#Sprite()
     *
     * Create a {@link SlidableSprite}.
     * At constructor time the sprite height is less than the maxHeight
     *
     * @param maxWidth the max width that the sprite can have
     * @param resize   if the sprite has to be resized
     *                 is false and getHeight() >= maxHeight return true or getHeight() == 0,
     *                 then {@link SlidableSprite#resize(float)} is called
     */
    public SlidableSprite(Texture texture, float maxWidth, boolean resize) {
        this(texture, 1, maxWidth, resize);
    }

    /**
     *
     * @see Sprite#Sprite()
     *
     * Create a {@link SlidableSprite}.
     * At constructor time the sprite height is less than the maxHeight
     *
     * @param maxWidth the max width that the sprite can have
     * @param resize   if the sprite has to be resized
     *                 is false and getHeight() >= maxHeight return true or getHeight() == 0,
     *                 then {@link SlidableSprite#resize(float)} is called
     */
    public SlidableSprite(Texture texture, float scaleFactor, float maxWidth, boolean resize) {
        super(texture, scaleFactor);

        if (resize || getWidth() >= maxWidth || getWidth() == 0)
            resize(maxWidth);
    }


    /**
     *
     * Create a {@link SlidableSprite} starting from another {@link Sprite}
     * and resize it so that is jumpable
     *
     * @param sprite the sprite that has to be jumpable
     * @param maxWidth the max width that the sprite can have
     * @param resize   if the sprite has to be resized
     *                 is false and getHeight() >= maxHeight return true or getHeight() == 0,
     *                 then {@link SlidableSprite#resize(float)} is called
     */
    public SlidableSprite(Sprite sprite, float maxWidth, boolean resize) {
        super(sprite.getScaleFactor());

        set(sprite);

        if (resize || getWidth() >= maxWidth || getWidth() == 0)
            resize(maxWidth);

    }



    /* -------------------------------- SERVICE METHODS -------------------------------- */

    /**
     *
     * Resize sprite in random mode so that his width is less than maxWidth
     */
    private void resize(float maxWidth) {
        var minW = Math.min(getWidth(), maxWidth * 0.5 - 1);
        var maxW = maxWidth - 1;

        float newW = (float)ThreadLocalRandom.current().nextDouble(minW, maxW);
        setSize(newW, getHeight());
    }

}
