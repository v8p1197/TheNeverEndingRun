package it.unisa.theneverendingrun.models.spawnables.obstacle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.spawnables.Collidable;
import it.unisa.theneverendingrun.models.spawnables.Spawnable;
import org.mini2Dx.core.engine.geom.CollisionBox;

public abstract class Obstacle extends Sprite implements Spawnable {


    /* ------------------------------------- PARAMS ------------------------------------- */

    /**
     *
     * Index of the collision rectangle
     * TODO: change collision box
     */
    private static final int left = 2, top = 3, right = 0, bottom = 1;

    /**
     *
     * Store the jump height of the object that need to jump over the obstacle
     */
    private final float jumpHeight;

    /**
     *
     * Store the slide distance of the object that need to slide over the obstacle
     */
    private final float slideDistance;



    /* ------------------------------------- CONSTRUCTORS ------------------------------------- */

    /**
     *
     * Obstacle constructor. Call the super and set the jumpHeight and the slideDistance
     *
     * @param jumpHeight the jump height of the object that need to jump over the obstacle
     * @param slideDistance the slide distance of the object that need to slide over the obstacle
     */
    public Obstacle(Texture texture, float jumpHeight, float slideDistance) {
        this(texture, 1, jumpHeight, slideDistance);
    }

    /**
     *
     * Obstacle constructor. Call the super and set the jumpHeight and the slideDistance
     *
     * @param jumpHeight the jump height of the object that need to jump over the obstacle
     * @param slideDistance the slide distance of the object that need to slide over the obstacle
     */
    public Obstacle(Texture texture, float scaleFactor, float jumpHeight, float slideDistance) {
        super(texture, scaleFactor);
        this.jumpHeight = jumpHeight;
        this.slideDistance = slideDistance;
    }



    /* ------------------------------------- GETTERS ------------------------------------- */

    /**
     *
     * @see Obstacle#jumpHeight
     * @return the jump height of the object that need to jump over the obstacle
     */
    @Override
    public float getJumpHeight() {
        return this.jumpHeight;
    }

    /**
     *
     * @see Obstacle#slideDistance
     * @return the slide distance of the object that need to slide over the obstacle
     */
    @Override
    public float getSlideDistance() {
        return slideDistance;
    }



    /* ------------------------------------- CHECK ------------------------------------- */

    /**
     *
     * @param hero the hero that can collide with the obstacle
     * @return true if the obstacle collide with the hero
     */
    @Override
    public boolean isColliding(Hero hero) {
        return getCollisionBox().intersects(hero.getCollisionBox());
    }



    /* ------------------------------------- COLLISION ------------------------------------- */

    /**
     *
     * What the obstacle have to do when the hero collide with the obstacle
     *
     * @param hero the hero that collide with the obstacle
     */
    @Override
    public void beginColliding(Hero hero) {
        var obstacleCollisionBox = this.getCollisionBox();
        var heroCollisionBox = hero.getCollisionBox();

        var collision = collisionSide(hero, obstacleCollisionBox);
        var intersection = heroCollisionBox.intersection(obstacleCollisionBox);

        if (collision == right) {
            hero.setX(hero.getX() + intersection.getWidth());
        } else if (collision == left) {
            hero.setX(hero.getX() - intersection.getWidth());
        } else if (collision == bottom) {
            //CollisionManager.wasOnObstacle.put(this, true);
            if (hero.isJumping() && hero.getJumpCompletion() >= 0.5 || hero.isFalling())
                hero.getMoveState().onIdle();
            hero.setY(hero.getY() + intersection.getHeight());
        } else if (collision == top) {

            if (hero.getX() < this.getX()) // if the hero is left with respect to the spawnable
                hero.setX(hero.getX() - intersection.getWidth());

            else if (hero.getX() > this.getX() + this.getWidth()) // if the hero is right with respect to the spawnable
                hero.setX(hero.getX() + intersection.getWidth());

                // if the hero is under the spawnable and was sliding, but there is not enough space to stand
            else if (this.getY() - hero.getGroundY() < hero.getHeight()) {
                hero.getMoveState().onSlide();
            } else {
                hero.setY(hero.getY() - intersection.getHeight());
                hero.getMoveState().onFall();
            }
        }
    }

    /**
     *
     * What the obstacle have to do when collision with hero end
     *
     * @param hero the hero that collide with the obstacle
     */
    @Override
    public void endColliding(Hero hero) {
      /*  if (!CollisionManager.wasOnObstacle.containsKey(this))
            CollisionManager.wasOnObstacle.put(this, false);
        else {
            if (CollisionManager.wasOnObstacle.get(this) && !hero.isJumping()) {
                hero.getMoveState().onFall();
                CollisionManager.wasOnObstacle.put(this, false);
            }
        }*/
    }



    /* ------------------------------------- SERVICE METHOD ------------------------------------- */

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
