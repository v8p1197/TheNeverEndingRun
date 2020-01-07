package it.unisa.theneverendingrun.models;

import java.util.concurrent.ThreadLocalRandom;

public class JumpableSprite extends Sprite {

    /**
     * The sprite that has to be resized so is jumpable
     */
    private Sprite sprite;


    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     * Resize a sprite so that is jumpable
     *
     * @param sprite    the sprite that has to be resized so is jumpable
     * @param maxHeight the max height that the sprite can have
     */
    public JumpableSprite(Sprite sprite, float maxHeight) {
        this(sprite, maxHeight, true);
    }

    /**
     *
     * Resize a sprite so that is jumpable
     *
     * @param sprite    the sprite that has to be resized so is jumpable
     * @param maxHeight the max height that the sprite can have
     * @param resize    if the sprite has to be resized.
     *                  If is false and getHeight() >= maxHeight return true or getHeight() == 0,
     *                  then {@link JumpableSprite#resize(float)} is called
     */
    public JumpableSprite(Sprite sprite, float maxHeight, boolean resize) {
        super(sprite.getScaleFactor());

        set(sprite);
        this.sprite = sprite;

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


    @Override
    public void accept(Visitor visitor) {
        sprite.accept(visitor);
    }
}
