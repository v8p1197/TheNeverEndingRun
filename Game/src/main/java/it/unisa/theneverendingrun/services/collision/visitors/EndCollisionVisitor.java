package it.unisa.theneverendingrun.services.collision.visitors;

import it.unisa.theneverendingrun.services.Visitor;
import it.unisa.theneverendingrun.models.background.Background;
import it.unisa.theneverendingrun.models.enemy.Enemy;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.obstacle.Obstacle;
import it.unisa.theneverendingrun.models.powerup.AbstractPowerUp;
import it.unisa.theneverendingrun.services.collision.CollisionManager;

public class EndCollisionVisitor implements Visitor {

    private Hero hero;

    public EndCollisionVisitor(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void visitEnemy(Enemy enemy) {
        enemy.getState().onIdle();
    }

    @Override
    public void visitObstacle(Obstacle sprite) {
        if (CollisionManager.wasOnObstacle && !hero.isJumping())
            hero.getMoveState().onFall();
        CollisionManager.wasOnObstacle = false;

        System.out.println("collision with obstacle ended");
    }

    @Override
    public void visitPowerUp(AbstractPowerUp sprite) {

    }

    @Override
    public void visitHero(Hero sprite) {

    }

    @Override
    public void visitBackground(Background background) {

    }
}
