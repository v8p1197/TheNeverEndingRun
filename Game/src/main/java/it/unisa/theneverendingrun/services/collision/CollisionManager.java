package it.unisa.theneverendingrun.services.collision;

import it.unisa.theneverendingrun.models.Sprite;
import it.unisa.theneverendingrun.models.Visitor;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.services.collision.visitors.BeginCollisionVisitor;
import it.unisa.theneverendingrun.services.collision.visitors.EndCollisionVisitor;
import it.unisa.theneverendingrun.utilities.CollisionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CollisionManager {

    public static boolean wasOnObstacle;

    private List<Sprite> collidingSprites;
    private Visitor beginCollisionVisitor;
    private Visitor endCollisionVisitor;

    private Hero hero;

    public CollisionManager(Hero hero) {
        this.hero = hero;
        collidingSprites = new LinkedList<>();
        beginCollisionVisitor = new BeginCollisionVisitor(hero);
        endCollisionVisitor = new EndCollisionVisitor(hero);

        wasOnObstacle = false;
    }

    public void checkCollision(List<Sprite> sprites) {

        collidingSprites.stream()
                .filter(sprite -> sprite != null && !CollisionUtils.areColliding(hero, sprite))
                .forEach(this::endCollision);

        collidingSprites = sprites.stream()
                .filter(sprite -> CollisionUtils.areColliding(hero, sprite))
                .peek(this::beginCollision)
                .collect(Collectors.toList());
    }

    private void beginCollision(Sprite sprite) {
        sprite.accept(beginCollisionVisitor);
//        switch (type) {
//
//            case OBSTACLE:
//                var obstacle = (Obstacle) sprite;
//
//                var side = CollisionUtils.collisionSide(hero.getCollisionBox(), obstacle.getCollisionBox());
//
//                if (side == CollisionSideType.BOTTOM)
//                    wasOnObstacle = true;
//
//                var sideStrategy = collisionSideStrategyFactory.createCollisionSideStrategy(side);
//                sideStrategy.collide(obstacle);
//
//                break;
//
//            case ENEMY:
//                var enemy = (Enemy) sprite;
//                enemy.getState().onAttack();
//                var swordStrategy = powerUpStrategyFactory.createPowerUpStrategy(PowerUpType.SWORD);
//                var killed = swordStrategy.consume();
//
//                if (killed) {
//                    enemy.getState().onDie();
//                } else {
//                    hero.die();
//                }
//
//                break;
//
//            case POWER_UP:
//                var powerUp = ((AbstractPowerUp) sprite);
//                var powerUpType = powerUp.getPowerUpType();
//                var strategy = powerUpStrategyFactory.createPowerUpStrategy(powerUpType);
//                var collected = strategy.collect();
//                if (collected) powerUp.setVisible(false);
//
//                break;
//
//            default:
//
//        }
    }

    private void endCollision(Sprite sprite) {
        sprite.accept(endCollisionVisitor);
//        var type = sprite.getSpriteImplType();
//
//        switch (type) {
//
//            case OBSTACLE:
//                if (wasOnObstacle)
//                    hero.getMoveState().onFall();
//                wasOnObstacle = false;
//
//                break;
//
//            case ENEMY:
//                var enemy = (Enemy) sprite;
//
//                enemy.getState().onIdle();
//
//                break;
//
//            default:
//
//        }
    }


}
