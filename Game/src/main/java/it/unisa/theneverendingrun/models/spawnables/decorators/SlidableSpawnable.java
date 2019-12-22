package it.unisa.theneverendingrun.models.spawnables.decorators;

import com.badlogic.gdx.graphics.g2d.Batch;
import it.unisa.theneverendingrun.models.spawnables.Spawnable;

import java.util.concurrent.ThreadLocalRandom;

public class SlidableSpawnable implements Spawnable {

    private Spawnable spawnable;

    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     *
     * Resize a spawnable so that is slidable
     *
     * @param spawnable the spawnable that has to be jumpable
     */
    public SlidableSpawnable(Spawnable spawnable) {
        this(spawnable, true);
    }

    /**
     *
     * Resize a spawnable so that is slidable if alwaysResize is true or if isWidthSlidable return false
     *
     * @param spawnable the spawnable that has to be slidable
     * @param alwaysResize if the spawnable has be resized also when isWidthSlidable return true
     */
    public SlidableSpawnable(Spawnable spawnable, boolean alwaysResize) {
        this.spawnable = spawnable;

        if(alwaysResize)
            resize();
        else if (!isWidthSlidable())
            resize();
    }



    /* -------------------------------- SERVICE METHODS -------------------------------- */

    /**
     * Resize spawnable in random mode so that is slidable
     */
    private void resize() {
        var minW = Math.min(getWidth(), getSlideDistance() * 0.5 - 1);
        var maxW = getSlideDistance() - 1;

        float newW = (float)ThreadLocalRandom.current().nextDouble(minW, maxW);
        setSize(newW, getHeight());
    }

    /**
     * Draw the spawnable through the batch
     *
     * @param batch the batch that is capable of draw the spawnable
     */
    @Override
    public void draw(Batch batch) {
        spawnable.draw(batch);
    }



    /* -------------------------------- GETTERS -------------------------------- */

    /**
     * @return the jump height of the object that need to jump over the spawnable
     */
    @Override
    public float getJumpHeight() {
        return spawnable.getJumpHeight();
    }

    /**
     * @return the slide distance of the object that need to slide over the spawnable
     */
    @Override
    public float getSlideDistance() {
        return spawnable.getSlideDistance();
    }

    /**
     * @return the width of the spawnable
     */
    @Override
    public float getWidth() {
        return spawnable.getWidth();
    }

    /**
     * @return the height of the spawnable
     */
    @Override
    public float getHeight() {
        return spawnable.getHeight();
    }

    /**
     * @return the coordinate x of the spawnable
     */
    @Override
    public float getX() {
        return spawnable.getX();
    }

    /**
     * @return the coordinate y of the spawnable
     */
    @Override
    public float getY() {
        return spawnable.getY();
    }



    /* -------------------------------- SETTERS -------------------------------- */

    /**
     * Set the size of the spawnable
     *
     * @param width  the width of the spawnable
     * @param height the height of the spawnable
     */
    @Override
    public void setSize(float width, float height) {
        if (width >= getSlideDistance()) throw new IllegalArgumentException("width is not slidable");
        spawnable.setSize(width, height);
    }

    /**
     * Set the position on the x and y Axis
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    @Override
    public void setPosition(float x, float y) {
        spawnable.setPosition(x, y);
    }



    /* -------------------------------- CHECK -------------------------------- */

    /**
     * @return the true if the height of the spawnable is such that the spawnable is jumpable
     */
    @Override
    public boolean isHeightJumpable() {
        return spawnable.isHeightJumpable();
    }

    /**
     * @return the true if the width of the spawnable is such that the spawnable is slidable
     */
    @Override
    public boolean isWidthSlidable() {
        return getWidth() < getSlideDistance();
    }

    /**
     * @return the true if the width of the spawnable is such that the spawnable is slidable if near another spawnable
     */
    @Override
    public boolean isWidthMultipleSlidable() {
        return getWidth() < getSlideDistance() * 0.5;
    }

    /**
     * @return true if the spwnable is visible on the X Axis
     */
    @Override
    public boolean isXAxisVisible() {
        return spawnable.isXAxisVisible();
    }
}
