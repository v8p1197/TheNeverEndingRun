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
    public static Map<Sprite, Boolean> wasOnObstacle = new HashMap<>();

    public static int left = 2, top = 3, right = 0, bottom = 1;

    public static void checkCollision(Hero hero, Sprite obstacle) {
        var obstacleCollisionBox = obstacle.getCollisionBox();
        var heroCollisionBox = hero.getCollisionBox();

        if (heroCollisionBox.intersects(obstacleCollisionBox)) {
            var collision = collisionSide(hero, obstacleCollisionBox);
            var intersection = heroCollisionBox.intersection(obstacleCollisionBox);

            if (collision == right) {
                hero.setX(hero.getX() + intersection.getWidth());
            } else if (collision == left) {
                hero.setX(hero.getX() - intersection.getWidth());
            } else if (collision == bottom) {
                wasOnObstacle.put(obstacle, true);
                if (hero.isJumping() || hero.isFalling())
                    hero.getMoveState().onIdle();
                hero.setY(hero.getY() + intersection.getHeight());
            } else if (collision == top) {

                if (hero.getX() < obstacle.getX()) // if the hero is left with respect to the obstacle
                    hero.setX(hero.getX() - intersection.getWidth());

                else if (hero.getX() > obstacle.getX() + obstacle.getWidth()) // if the hero is right with respect to the obstacle
                    hero.setX(hero.getX() + intersection.getWidth());

                    // if the hero is under the obstacle and was sliding, but there is not enough space to stand
                else if (obstacle.getY() - hero.getGroundY() < hero.getStandardHeight()) {
                    hero.getMoveState().onSlide();
                } else {
                    hero.setY(hero.getY() - intersection.getHeight());
                    hero.getMoveState().onFall();
                }
            }
        } else {
            if (!wasOnObstacle.containsKey(obstacle))
                wasOnObstacle.put(obstacle, false);
            else {
                if (wasOnObstacle.get(obstacle) && !hero.isJumping()) {
                    hero.getMoveState().onFall();
                    wasOnObstacle.put(obstacle, false);
                }
            }
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
