package it.unisa.theneverendingrun.models.spawnables;

import com.badlogic.gdx.graphics.g2d.Batch;
import it.unisa.theneverendingrun.models.hero.Hero;
import org.mini2Dx.core.engine.geom.CollisionBox;

public interface Spawnable {


    /* ------------------------------------- GETTERS ------------------------------------- */

    /**
     *
     * @return the jump height of the object that need to jump over the spawnable
     */
    float getJumpHeight();

    /**
     *
     * @return the slide distance of the object that need to slide over the spawnable
     */
    float getSlideDistance();

    /**
     *
     * @return the width of the spawnable
     */
    float getWidth();

    /**
     *
     * @return the height of the spawnable
     */
    float getHeight();

    /**
     *
     * @return the coordinate x of the spawnable
     */
    float getX();

    /**
     *
     * @return the coordinate y of the spawnable
     */
    float getY();

    /**
     *
     * @return the collision box of the spawnable
     */
    CollisionBox getCollisionBox();



    /* ------------------------------------- SETTERS ------------------------------------- */

    /**
     *
     * Set the size of the spawnable
     * @param width the width of the spawnable
     * @param height the height of the spawnable
     */
    void setSize(float width, float height);

    /**
     *
     * Set the position on the x and y Axis
     * @param x the x coordinate
     * @param y the y coordinate
     */
    void setPosition(float x, float y);



    /* ------------------------------------- CHECK ------------------------------------- */

    /**
     *
     * @return the true if the height of the spawnable is such that the spawnable is jumpable
     */
    default boolean isHeightJumpable() {
        return getHeight() < getJumpHeight();
    }

    /**
     *
     * @return the true if the width of the spawnable is such that the spawnable is slidable
     */
    default boolean isWidthSlidable() {
        return getWidth() < getSlideDistance();
    }

    /**
     *
     * @return the true if the width of the spawnable is such that the spawnable is slidable if near another spawnable
     */
    default boolean isWidthMultipleSlidable() {
        return getWidth() < getSlideDistance() * 0.5;
    }

    /**
     *
     * @return true if the spwnable is visible on the X Axis
     */
    boolean isXAxisVisible();



    /* ------------------------------------- COLLISION ------------------------------------- */

    /**
     *
     * What the spawnable have to do when the hero collide with the spawnable
     * @param hero the hero that collide with the spawnable
     */
    void beginCollision(Hero hero);

    /**
     *
     * What the spawnable have to do when collision with hero end
     * @param hero the hero that collide with the spawnable
     */
    void endCollision(Hero hero);



    /* ------------------------------------- SERVICE METHODS ------------------------------------- */

    /**
     *
     * Draw the spawnable through the batch
     * @param batch the batch that is capable of draw the spawnable
     */
    void draw(Batch batch);



}
