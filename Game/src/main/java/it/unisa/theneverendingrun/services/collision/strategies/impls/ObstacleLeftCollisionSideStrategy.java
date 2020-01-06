package it.unisa.theneverendingrun.services.collision.strategies.impls;

import it.unisa.theneverendingrun.models.hero.AbstractHero;
import it.unisa.theneverendingrun.models.obstacle.AbstractObstacle;
import it.unisa.theneverendingrun.services.collision.strategies.CollisionSideStrategy;
import it.unisa.theneverendingrun.utilities.CollisionUtils;

public class ObstacleLeftCollisionSideStrategy extends CollisionSideStrategy<AbstractObstacle> {

    public ObstacleLeftCollisionSideStrategy(AbstractHero hero) {
        super(hero);
    }

    @Override
    public void collide(AbstractObstacle obstacle) {
        var intersection = CollisionUtils.computeIntersection(hero, obstacle);
        hero.setX(hero.getX() + intersection.getWidth());
    }
}
