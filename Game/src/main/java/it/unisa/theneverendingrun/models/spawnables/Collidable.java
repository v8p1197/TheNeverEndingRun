package it.unisa.theneverendingrun.models.spawnables;

import it.unisa.theneverendingrun.models.hero.Hero;
import org.mini2Dx.core.engine.geom.CollisionBox;

public interface Collidable {

    /* ------------------------------------- GETTERS ------------------------------------- */

    /**
     *
     * @return the collision box of the spawnable
     */
    CollisionBox getCollisionBox();



    /* ------------------------------------- CHECK ------------------------------------- */

    /**
     *
     * @param hero the hero that can collide with the collidable
     * @return true if the collidable collide with the hero
     */
    boolean isColliding(Hero hero);



    /* ------------------------------------- COLLISION ------------------------------------- */

    /**
     *
     * What the collidable have to do when the hero collide with the collidable
     * @param hero the hero that collide with the collidable
     */
    void beginColliding(Hero hero);

    /**
     *
     * What the collidable have to do when collision with hero end
     * @param hero the hero that collide with the collidable
     */
    void endColliding(Hero hero);
}
