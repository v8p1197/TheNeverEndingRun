package it.unisa.theneverendingrun.services.spawn.position;

import com.badlogic.gdx.Gdx;
import it.unisa.theneverendingrun.models.Visitor;
import it.unisa.theneverendingrun.models.background.Background;
import it.unisa.theneverendingrun.models.enemy.Enemy;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.obstacle.Obstacle;
import it.unisa.theneverendingrun.models.powerup.AbstractPowerUp;

public class PositioningVisitor implements Visitor {

    private float screenWidth;

    public PositioningVisitor(float screenWidth) {
        this.screenWidth = screenWidth;
    }

    @Override
    public void visitEnemy(Enemy sprite) {
        sprite.setY(0);
        sprite.setX(screenWidth);
    }

    @Override
    public void visitObstacle(Obstacle sprite) {

    }

    @Override
    public void visitPowerUp(AbstractPowerUp sprite) {
        sprite.setY(0);
        sprite.setX(screenWidth);
    }

    @Override
    public void visitHero(Hero sprite) {

    }

    @Override
    public void visitBackground(Background background) {

    }
}
