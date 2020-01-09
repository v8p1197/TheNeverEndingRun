package it.unisa.theneverendingrun.services.collision.strategies.impls;

import it.unisa.theneverendingrun.engine.state.PlayState;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.obstacle.Obstacle;
import it.unisa.theneverendingrun.models.powerup.PowerUpType;
import it.unisa.theneverendingrun.models.powerup.strategies.PowerUpStrategyFactory;
import it.unisa.theneverendingrun.models.powerup.strategies.impls.MultiplierPowerUpMetersListener;
import it.unisa.theneverendingrun.services.collision.strategies.CollisionSideStrategy;
import it.unisa.theneverendingrun.utilities.CollisionUtils;

public class ObstacleRightCollisionSideStrategy extends CollisionSideStrategy<Obstacle> {

    public ObstacleRightCollisionSideStrategy(Hero hero) {
        super(hero);
    }

    @Override
    public void collide(Obstacle obstacle) {
        var intersection = CollisionUtils.computeIntersection(hero, obstacle);

        var consumed = new PowerUpStrategyFactory(hero).createPowerUpStrategy(PowerUpType.SHIELD, null).consume();

        if (consumed) {
            obstacle.setVisible(false);
            final var increment = 0.1F;
            PlayState.scoreMetersListener.setMultiplier(PlayState.scoreMetersListener.getMultiplier() +
                    MultiplierPowerUpMetersListener.getInstance().getMultiplier() * increment);
        } else
            hero.setX(hero.getX() - intersection.getWidth());
    }
}
