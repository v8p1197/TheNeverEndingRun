package it.unisa.theneverendingrun.services.collision.strategies.impls;

import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.obstacle.Obstacle;
import it.unisa.theneverendingrun.services.collision.strategies.CollisionSideStrategy;
import it.unisa.theneverendingrun.utilities.CollisionUtils;

public class ObstacleLeftCollisionSideStrategy extends CollisionSideStrategy<Obstacle> {

    public ObstacleLeftCollisionSideStrategy(Hero hero) {
        super(hero);
    }

    @Override
    public void collide(Obstacle obstacle) {
        var intersection = CollisionUtils.computeIntersection(hero, obstacle);
        hero.setX(hero.getX() + intersection.getWidth());
    }
}
