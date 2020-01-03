package it.unisa.theneverendingrun.models;

import java.util.concurrent.ThreadLocalRandom;

public class JumpableSprite extends Sprite {


    /* ------------------------------------- PARAMS ------------------------------------- */

    /**
     *
     * The value that say if the spawnable is resized
     */
    private boolean isResized;




    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     *
     * Resize a sprite so that is jumpable
     *
     * @param sprite the sprite that has to be jumpable
     * @param maxHeight the max height that the sprite can have
     */
    public JumpableSprite(Sprite sprite, float maxHeight) {
        this(sprite, maxHeight, true);
    }

    /**
     *
     * Resize a sprite so that is jumpable
     *
     * @param sprite the sprite that has to be jumpable
     * @param maxHeight the max height that the sprite can have
     * @param alwaysResize if the sprite has to be resized.
     *                     If is false and getHeight() >= maxHeight return true or getHeight() == 0,
     *                     then {@link JumpableSprite#resize(float)} is called
     */
    public JumpableSprite(Sprite sprite, float maxHeight, boolean alwaysResize) {
        super(sprite.getScaleFactor());

        this.isResized = false;

        set(sprite);

        if (alwaysResize || getHeight() >= maxHeight || getHeight() == 0)
            resize(maxHeight);

        isResized = true;
    }




    /* -------------------------------- SERVICE METHODS -------------------------------- */

    /**
     *
     * Resize spawnable in random mode so that is jumpable
     */
    private void resize(float maxHeight) {
        var minH = Math.min(getHeight(), maxHeight * 0.5 - 1);
        var maxH = maxHeight - 1;

        float newH = (float) ThreadLocalRandom.current().nextDouble(minH, maxH);
        setSize(getWidth(), newH);
    }




    /* -------------------------------- SETTERS -------------------------------- */

    /**
     *
     * Set the size of the spawnable
     *
     * @param width  the width of the spawnable
     * @param height the height of the spawnable
     */
    @Override
    public void setSize(float width, float height) {
        if (isResized) return;
        super.setSize(width, height);
        isResized = true;
    }
}
