package it.unisa.theneverendingrun.services;

import it.unisa.theneverendingrun.models.background.Background;
import it.unisa.theneverendingrun.models.enemy.Enemy;
import it.unisa.theneverendingrun.models.hero.Hero;
import it.unisa.theneverendingrun.models.obstacle.Obstacle;
import it.unisa.theneverendingrun.models.powerup.AbstractPowerUp;

public interface Visitor {

    void visitEnemy(Enemy sprite);

    void visitObstacle(Obstacle sprite);

    void visitPowerUp(AbstractPowerUp sprite);

    void visitHero(Hero sprite);

    void visitBackground(Background background);
}
