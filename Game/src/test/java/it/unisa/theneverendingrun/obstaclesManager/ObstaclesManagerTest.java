package it.unisa.theneverendingrun.obstaclesManager;

import it.unisa.theneverendingrun.models.obstacles.AbstractObstacle;
import it.unisa.theneverendingrun.models.obstacles.JumpableObstacle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ObstaclesManagerTest {


    @Test
    void getObstacle() {
        ObstaclesManager obstaclesManager = new ObstaclesManager(100, 100, 100, 100, 100);
        AbstractObstacle abstractObstacle = obstaclesManager.getObstacle();
        //if the new obstacle is jumpable, check that it is so


        if (abstractObstacle instanceof JumpableObstacle) {
            assertTrue(abstractObstacle.getHeight() + abstractObstacle.getX() <= 100);
        }
    }

    @Test
    void getAppropriateObstacleType() {

    }

    @Test
    void setPosition() {
    }

    @Test
    void clearOldObstacles() {

    }
}