package it.unisa.theneverendingrun.services.collision.strategies.impls;

import it.unisa.theneverendingrun.models.hero.AbstractHero;
import it.unisa.theneverendingrun.models.obstacle.AbstractObstacle;
import it.unisa.theneverendingrun.services.collision.strategies.CollisionSideStrategy;
import it.unisa.theneverendingrun.utilities.CollisionUtils;

public class ObstacleBottomCollisionSideStrategy extends CollisionSideStrategy<AbstractObstacle> {

    public ObstacleBottomCollisionSideStrategy(AbstractHero hero) {
        super(hero);
    }

    @Override
    public void collide(AbstractObstacle obstacle) {
        var intersection = CollisionUtils.computeIntersection(hero, obstacle);
        hero.setY(hero.getY() + intersection.getHeight());
        if (hero.isJumping() && hero.getJumpCompletion() >= 0.5 || hero.isFalling())
            hero.getMoveState().onIdle();
    }
}
