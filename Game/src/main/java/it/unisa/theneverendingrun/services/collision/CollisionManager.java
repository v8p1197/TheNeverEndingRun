package it.unisa.theneverendingrun.services.collision;

import it.unisa.theneverendingrun.models.Spawnable;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.obstacles.AbstractObstacle;

import java.util.HashMap;
import java.util.Map;

public class CollisionManager {

    /**
     * A boolean variable true when the hero is on on obstacle in the previous move step
     */
    public static Map<AbstractObstacle, Boolean> wasOnObstacle = new HashMap<>();

    public static void checkCollision(Hero hero, Spawnable spawnable) {
        var spawnableCollisionBox = spawnable.getCollisionBox();
        var heroCollisionBox = hero.getCollisionBox();

        if (heroCollisionBox.intersects(spawnableCollisionBox)) {
            spawnable.beginCollision(hero);
        } else {
            spawnable.endCollision(hero);
        }
    }


}
