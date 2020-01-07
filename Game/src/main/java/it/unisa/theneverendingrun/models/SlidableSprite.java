package it.unisa.theneverendingrun.models;


import java.util.concurrent.ThreadLocalRandom;

public class SlidableSprite extends Sprite {

    /**
     * The sprite that has to be resized so is jumpable
     */
    private Sprite sprite;

    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     * Resize a sprite so that is slidable
     *
     * @param sprite   the sprite that has to be resized so is slidable
     * @param maxWidth the max width that the sprite can have
     */
    public SlidableSprite(Sprite sprite, float maxWidth) {
        this(sprite, maxWidth, true);
    }

    /**
     *
     * Resize a sprite so that is slidable
     *
     * @param sprite   the sprite that has to be resized so is slidable
     * @param maxWidth the max width that the sprite can have
     * @param resize   if the sprite has to be resized
     *                 is false and getHeight() >= maxHeight return true or getHeight() == 0,
     *                 then {@link SlidableSprite#resize(float)} is called
     */
    public SlidableSprite(Sprite sprite, float maxWidth, boolean resize) {
        super(sprite.getScaleFactor());

        set(sprite);
        this.sprite = sprite;

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

    @Override
    public void accept(Visitor visitor) {
        sprite.accept(visitor);
    }
}
