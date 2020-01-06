package it.unisa.theneverendingrun.services.collision.strategies.impls;

import it.unisa.theneverendingrun.models.hero.AbstractHero;
import it.unisa.theneverendingrun.models.obstacle.Obstacle;
import it.unisa.theneverendingrun.services.collision.CollisionSideType;
import it.unisa.theneverendingrun.services.collision.strategies.CollisionSideStrategy;
import it.unisa.theneverendingrun.services.collision.strategies.ObstacleCollisionSideStrategyFactory;
import it.unisa.theneverendingrun.utilities.CollisionUtils;

public class ObstacleTopCollisionSideStrategy extends CollisionSideStrategy<Obstacle> {

    public ObstacleTopCollisionSideStrategy(AbstractHero hero) {
        super(hero);
    }

    @Override
    public void collide(Obstacle obstacle) {
        var intersection = CollisionUtils.computeIntersection(hero, obstacle);

        if (hero.isJumping()) {
            hero.setY(hero.getY() - intersection.getHeight());
            hero.getMoveState().onFall();
            return;
        }

        if (hero.isIdle()) {
            // if the hero is left with respect to the spawnable
            if (hero.getX() < intersection.getX())
                new ObstacleCollisionSideStrategyFactory(hero).createCollisionSideStrategy(CollisionSideType.RIGHT).collide(obstacle);

            // if the hero is right with respect to the spawnable
            else if (hero.getX() > intersection.getX() + intersection.getWidth())
                new ObstacleCollisionSideStrategyFactory(hero).createCollisionSideStrategy(CollisionSideType.LEFT).collide(obstacle);
        }

        // if the hero is under the spawnable and was sliding, but there is not enough space to stand
        if (hero.wasSliding())
            hero.getMoveState().onSlide();
    }
}
