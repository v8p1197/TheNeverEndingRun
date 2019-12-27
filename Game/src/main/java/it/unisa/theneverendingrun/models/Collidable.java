package it.unisa.theneverendingrun.models;

import it.unisa.theneverendingrun.models.hero.Hero;
import org.mini2Dx.core.engine.geom.CollisionBox;

public interface Collidable {

    /* ------------------------------------- GETTERS ------------------------------------- */

    /**
     *
     * @see CollisionBox
     * @return the collision box of the collidable
     */
    CollisionBox getCollisionBox();



    /* ------------------------------------- CHECK ------------------------------------- */

    /**
     *
     * @see Hero
     * @param hero the hero that can collide with the collidable
     * @return true if the collidable collide with the hero
     */
    boolean isColliding(Hero hero);



    /* ------------------------------------- COLLISION ------------------------------------- */

    /**
     *
     * @see Hero
     * What the collidable have to do when the hero collide with the collidable
     * @param hero the hero that collide with the collidable
     */
    void beginColliding(Hero hero);

    /**
     *
     * @see Hero
     * What the collidable have to do when collision with hero end
     * @param hero the hero that collide with the collidable
     */
    void endColliding(Hero hero);
}
