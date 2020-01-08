package it.unisa.theneverendingrun.services.collision.strategies.impls;

import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.obstacle.Obstacle;
import it.unisa.theneverendingrun.services.collision.strategies.CollisionSideStrategy;
import it.unisa.theneverendingrun.utilities.CollisionUtils;

public class ObstacleBottomCollisionSideStrategy extends CollisionSideStrategy<Obstacle> {

    public ObstacleBottomCollisionSideStrategy(Hero hero) {
        super(hero);
    }

    @Override
    public void collide(Obstacle obstacle) {
        var intersection = CollisionUtils.computeIntersection(hero, obstacle);
        hero.setY(hero.getY() + intersection.getHeight());
        if (hero.isFalling())
            hero.getMoveState().onIdle();
    }
}
