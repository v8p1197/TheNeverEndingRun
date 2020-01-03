package it.unisa.theneverendingrun.models;


import java.util.concurrent.ThreadLocalRandom;

public class SlidableSprite extends Sprite {


    /* ------------------------------------- PARAMS ------------------------------------- */

    /**
     *
     * The value that say if the spawnable is resized
     */
    private boolean isResized;




    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     *
     * Resize a sprite so that is slidable
     *
     * @param sprite the sprite that has to be slidable
     * @param maxWidth the max width that the sprite can have
     */
    public SlidableSprite(Sprite sprite, float maxWidth) {
        this(sprite,  maxWidth, true);
    }

    /**
     *
     * Resize a sprite so that is slidable
     *
     * @param sprite the sprite that has to be slidable
     * @param maxWidth the max width that the sprite can have
     * @param alwaysResize if the sprite has to be resized.
     *                     If is false and getWidth() >= maxWidth return true or getWidth() == 0,
     *                     then {@link SlidableSprite#resize(float)} is called
     */
    public SlidableSprite(Sprite sprite, float maxWidth, boolean alwaysResize) {
        super(sprite.getScaleFactor());

        this.isResized = false;

        set(sprite);

        if (alwaysResize || getWidth() >= maxWidth || getWidth() == 0)
            resize(maxWidth);

        isResized = true;
    }




    /* -------------------------------- SERVICE METHODS -------------------------------- */

    /**
     *
     * Resize spawnable in random mode so that is slidable
     */
    private void resize(float maxWidth) {
        var minW = Math.min(getWidth(), maxWidth * 0.5 - 1);
        var maxW = maxWidth - 1;

        float newW = (float)ThreadLocalRandom.current().nextDouble(minW, maxW);
        setSize(newW, getHeight());
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
