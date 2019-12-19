package it.unisa.theneverendingrun.models.obstacle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import it.unisa.theneverendingrun.CollisionManager;
import it.unisa.theneverendingrun.models.Spawnable;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.hero.Hero;
import org.mini2Dx.core.engine.geom.CollisionBox;

public class Obstacle extends Sprite implements Spawnable {

    private static final int left = 2, top = 3, right = 0, bottom = 1;


    private final float jumpHeight;
    private final float slideDistance;

    public Obstacle(Texture texture, float jumpHeight, float slideDistance) {
        super(texture);
        this.jumpHeight = jumpHeight;
        this.slideDistance = slideDistance;
    }


    //***************************** getters *****************************//

    @Override
    public float getJumpHeight() {
        return jumpHeight;
    }

    @Override
    public float getSlideDistance() {
        return slideDistance;
    }




    //***************************** check *****************************//

    @Override
    public boolean isJumpable() {
        return getHeight() < jumpHeight;
    }

    @Override
    public boolean isSlideable() {
        return getWidth() < slideDistance;
    }

    @Override
    public boolean isMultipleSlideable() {
        return getWidth() < slideDistance / 2;
    }



    //***************************** helpers *****************************//

    @Override
    public void beginCollision(Hero hero) {

        var obstacleCollisionBox = this.getCollisionBox();
        var heroCollisionBox = hero.getCollisionBox();

        var collision = collisionSide(hero, obstacleCollisionBox);
        var intersection = heroCollisionBox.intersection(obstacleCollisionBox);

        if (collision == right) {
            hero.setX(hero.getX() + intersection.getWidth());
        } else if (collision == left) {
            hero.setX(hero.getX() - intersection.getWidth());
        } else if (collision == bottom) {
            CollisionManager.wasOnObstacle.put(this, true);
            if (hero.isJumping() && hero.getJumpCompletion() >= 0.5 || hero.isFalling())
                hero.getMoveState().onIdle();
            hero.setY(hero.getY() + intersection.getHeight());
        } else if (collision == top) {

            if (hero.getX() < this.getX()) // if the hero is left with respect to the spawnable
                hero.setX(hero.getX() - intersection.getWidth());

            else if (hero.getX() > this.getX() + this.getWidth()) // if the hero is right with respect to the spawnable
                hero.setX(hero.getX() + intersection.getWidth());

                // if the hero is under the spawnable and was sliding, but there is not enough space to stand
            else if (this.getY() - hero.getGroundY() < hero.getStandardHeight()) {
                hero.getMoveState().onSlide();
            } else {
                hero.setY(hero.getY() - intersection.getHeight());
                hero.getMoveState().onFall();
            }
        }

    }

    @Override
    public void endCollision(Hero hero) {
        if (!CollisionManager.wasOnObstacle.containsKey(this))
            CollisionManager.wasOnObstacle.put(this, false);
        else {
            if (CollisionManager.wasOnObstacle.get(this) && !hero.isJumping()) {
                hero.getMoveState().onFall();
                CollisionManager.wasOnObstacle.put(this, false);
            }
        }
    }




    //***************************** private helpers *****************************//

    private int collisionSide(Hero hero, CollisionBox obstacle) {

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
