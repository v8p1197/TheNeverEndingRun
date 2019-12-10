package it.unisa.theneverendingrun;

import com.badlogic.gdx.math.Rectangle;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.hero.Hero;
import org.mini2Dx.core.engine.geom.CollisionBox;

import java.util.HashMap;
import java.util.Map;

public class CollisionManager {

    /**
     * A boolean variable true when the hero is on on obstacle in the previous move step
     */
    private static Map<Sprite, Boolean> wasOnObstacle = new HashMap<>();

    public static int left = 2, top = 3, right = 0, bottom = 1;

    public static void checkCollision(Hero hero, Sprite obstacle) {
        var obstacleCollisionBox = obstacle.getCollisionBox();
        var heroCollisionBox = hero.getCollisionBox();

        if (heroCollisionBox.intersects(obstacleCollisionBox)) {
            var collision = collisionSide(hero, obstacleCollisionBox);

            if (collision == right) {
                hero.setX(obstacle.getX() + obstacle.getWidth());
            } else if (collision == left) {
                hero.setX(obstacle.getX() - hero.getWidth());
            } else if (collision == bottom) {
                wasOnObstacle.put(obstacle, true);
                if (hero.isJumping() || hero.isFalling())
                    hero.getMoveState().onIdle();
                hero.setY(obstacle.getY() + obstacle.getHeight());
            } else if (collision == top) {
                hero.getMoveState().onFall();
            }
        } else {
            if (!wasOnObstacle.containsKey(obstacle) || wasOnObstacle.get(obstacle) && !hero.isJumping())
                hero.getMoveState().onFall();
            wasOnObstacle.put(obstacle, false);
        }
    }

    private static int collisionSide(Hero hero, CollisionBox obstacle) {

        var heroCollisionBox = hero.getCollisionBox();

        CollisionBox[] boxes = new CollisionBox[4];
        boxes[0] = new CollisionBox(heroCollisionBox.getX() - 1, heroCollisionBox.getY(), 1, heroCollisionBox.getHeight());
        boxes[1] = new CollisionBox(heroCollisionBox.getX(), heroCollisionBox.getY() - 1, heroCollisionBox.getWidth(), 1);
        boxes[2] = new CollisionBox(heroCollisionBox.getX() + heroCollisionBox.getWidth(), heroCollisionBox.getY(), 1, heroCollisionBox.getHeight());
        boxes[3] = new CollisionBox(heroCollisionBox.getX(), heroCollisionBox.getY() + heroCollisionBox.getHeight(), heroCollisionBox.getWidth(), 1);

        double greatestArea = 0;
        int greatest = -1;

        for (int i = 0; i < boxes.length; i++) {
            var intersection = obstacle.intersection(boxes[i]);

            var rectangle = new Rectangle(intersection.getX(), intersection.getY(), intersection.getWidth(), intersection.getHeight());

            var area = rectangle.area();
            if (area > greatestArea) {
                greatestArea = area;
                greatest = i;
            }
        }

        return greatest;
    }
}
