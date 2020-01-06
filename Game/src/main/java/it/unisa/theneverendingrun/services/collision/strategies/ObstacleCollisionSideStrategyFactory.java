package it.unisa.theneverendingrun.services.collision.strategies;

import it.unisa.theneverendingrun.models.hero.AbstractHero;
import it.unisa.theneverendingrun.models.obstacle.AbstractObstacle;
import it.unisa.theneverendingrun.services.collision.CollisionSideType;
import it.unisa.theneverendingrun.services.collision.strategies.impls.ObstacleBottomCollisionSideStrategy;
import it.unisa.theneverendingrun.services.collision.strategies.impls.ObstacleLeftCollisionSideStrategy;
import it.unisa.theneverendingrun.services.collision.strategies.impls.ObstacleRightCollisionSideStrategy;
import it.unisa.theneverendingrun.services.collision.strategies.impls.ObstacleTopCollisionSideStrategy;

public class ObstacleCollisionSideStrategyFactory {

    private AbstractHero hero;

    public ObstacleCollisionSideStrategyFactory(AbstractHero hero) {
        this.hero = hero;
    }

    public CollisionSideStrategy<AbstractObstacle> createCollisionSideStrategy(CollisionSideType type) {
        switch (type) {
            case RIGHT:
                return new ObstacleRightCollisionSideStrategy(hero);
            case LEFT:
                return new ObstacleLeftCollisionSideStrategy(hero);
            case TOP:
                return new ObstacleTopCollisionSideStrategy(hero);
            case BOTTOM:
                return new ObstacleBottomCollisionSideStrategy(hero);

            default:
                throw new IllegalArgumentException("Collision side type not valid");
        }
    }
}
