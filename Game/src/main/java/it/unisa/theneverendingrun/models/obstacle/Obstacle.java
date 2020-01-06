package it.unisa.theneverendingrun.models.obstacle;

import it.unisa.theneverendingrun.models.Sprite;

/**
 *
 * A wrapper for {@link Sprite}
 */
public class Obstacle extends Sprite {

    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     *
     * Create an {@link Obstacle} starting from another {@link Sprite}
     * @param sprite the original sprite
     */
    public Obstacle(Sprite sprite) {
        super(sprite.getScaleFactor());
        set(sprite);
    }
}
