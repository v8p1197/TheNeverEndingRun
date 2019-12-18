package it.unisa.theneverendingrun.models.obstacles;

import com.badlogic.gdx.graphics.Texture;
import it.unisa.theneverendingrun.CollisionManager;
import it.unisa.theneverendingrun.models.Spawnable;
import it.unisa.theneverendingrun.models.hero.Hero;

public abstract class AbstractObstacle extends Spawnable {


    AbstractObstacle(Texture texture) {
        super(texture);
    }

    @Override
    public void reactToCollision(Hero hero) {
        var obstacleCollisionBox = this.getCollisionBox();
        var heroCollisionBox = hero.getCollisionBox();

        var collision = CollisionManager.collisionSide(hero, obstacleCollisionBox);
        var intersection = heroCollisionBox.intersection(obstacleCollisionBox);

        if (collision == CollisionManager.right) {
            hero.setX(hero.getX() + intersection.getWidth());
        } else if (collision == CollisionManager.left) {
            hero.setX(hero.getX() - intersection.getWidth());
        } else if (collision == CollisionManager.bottom) {
            CollisionManager.wasOnObstacle.put(this, true);
            if (hero.isJumping() && hero.getJumpCompletion() >= 0.5 || hero.isFalling())
                hero.getMoveState().onIdle();
            hero.setY(hero.getY() + intersection.getHeight());
        } else if (collision == CollisionManager.top) {

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
    public void setSize(float width, float height) {
        super.setSize(width, height);
    }
}
