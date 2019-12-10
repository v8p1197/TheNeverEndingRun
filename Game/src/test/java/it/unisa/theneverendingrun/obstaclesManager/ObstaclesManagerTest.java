package it.unisa.theneverendingrun.obstaclesManager;

import de.tomgrill.gdxtesting.GdxTestRunner;
import it.unisa.theneverendingrun.Launcher;
import it.unisa.theneverendingrun.models.obstacles.AbstractObstacle;
import it.unisa.theneverendingrun.models.obstacles.JumpableObstacle;
import it.unisa.theneverendingrun.models.obstacles.ObstacleType;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.LinkedList;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)

public class ObstaclesManagerTest {

    private Random random = new Random(666);
    private static int OFFSET_MEASURE = 100;
    private static int DEFAULT_MEASURE = 100;

    //random distances
    private float maxJumpingHeight = OFFSET_MEASURE * 3;
    private float standingHeight = OFFSET_MEASURE;
    private float slidingHeight = standingHeight / 2;
    private float standingWidth = (float) OFFSET_MEASURE / 2;
    private float maxSlidingDistance = maxJumpingHeight;


    @Test
    public void testObstacleOnTheRightEdge() {
        //create an obstacles manager with the previous distances
        ObstaclesManager obstaclesManager = new ObstaclesManager(maxJumpingHeight, standingHeight, maxSlidingDistance, slidingHeight, standingWidth);

        //Now i have the obstacle manager. By calling two times the method getNewAppropriateObstacle, it will add a
        // new obstacle on the right edge, but it will not be visible. So, the second call should return null, since
        // we cannot add anything.
        assertNotNull(obstaclesManager.getNewAppropriateObstacle());
        assertNull(obstaclesManager.getAppropriateObstacleType());
    }

    @Test
    public void testAddJumpableAfterJumpable() {
        Launcher.main(new String[0]);
        //running main to open the main window. this is needed in order to get the screen width

        ObstaclesManager obstaclesManager = new ObstaclesManager(maxJumpingHeight, standingHeight, maxSlidingDistance, slidingHeight, standingWidth);

        //add a first obstacle. since we want to test the case in which the last obstacle is jumpable,
        // get new obstacle until the one returned is jumpable
        AbstractObstacle firstObstacle = obstaclesManager.getNewAppropriateObstacle();
        while (!(firstObstacle instanceof JumpableObstacle)) {
            //move the last obstacle to generate another, otherwise it will be null
            obstaclesManager.getObstacles().getLast().setPosition(0, 0);
            firstObstacle = obstaclesManager.getNewAppropriateObstacle();
        }

        //move the rigth edge of the obstacle to the right edge of the window
        firstObstacle.setPosition(firstObstacle.getX() - firstObstacle.getWidth() + 1, firstObstacle.getY());

        //the new obstacle type will be null or jumpable
        ObstacleType newType = obstaclesManager.getAppropriateObstacleType();
        assertTrue(newType == null || newType == ObstacleType.Jumpable);
    }

    @Test
    public void notEnoughSpaceTest() {
        Launcher.main(new String[0]);
        //running main to open the main window. this is needed in order to get the screen width
        ObstaclesManager obstaclesManager = new ObstaclesManager(maxJumpingHeight, standingHeight, maxSlidingDistance, slidingHeight, standingWidth);
        //add a first obstacle. since we want to test the case in which the last obstacle is jumpable,
        // get new obstacle until the one returned is jumpable
        AbstractObstacle firstObstacle = obstaclesManager.getNewAppropriateObstacle();

        //translating the new obstacle to a distance such that the hero cannot pass between
        firstObstacle.translate(-(firstObstacle.getWidth() + standingWidth - 1), 0f);
        //the new type must be null, since nothing can be generated
        ObstacleType newType = obstaclesManager.getAppropriateObstacleType();
        assertNull(newType);
    }

    @Test
    public void enoughSpaceTest() {
        Launcher.main(new String[0]);
        //running main to open the main window. this is needed in order to get the screen width
        ObstaclesManager obstaclesManager = new ObstaclesManager(maxJumpingHeight, standingHeight, maxSlidingDistance, slidingHeight, standingWidth);
        //add a first obstacle. since we want to test the case in which the last obstacle is jumpable,
        // get new obstacle until the one returned is jumpable
        AbstractObstacle firstObstacle = obstaclesManager.getNewAppropriateObstacle();

        //translating the new obstacle to a distance such that the hero can pass between
        firstObstacle.translate(-(firstObstacle.getWidth() + standingWidth * ObstaclesManager.MULTIPLIER + 1), 0f);
        //the new type must be null, since nothing can be generated
        ObstacleType newType = obstaclesManager.getAppropriateObstacleType();
        while (newType == null) {
            newType = obstaclesManager.getAppropriateObstacleType();
        }
        assertNotNull(newType);
    }

    @Test
    public void getAppropriateObstacleType() {

        ObstaclesManager obstaclesManager = new ObstaclesManager(maxJumpingHeight, standingHeight, maxSlidingDistance, slidingHeight, standingWidth);
        LinkedList<AbstractObstacle> obstacles = obstaclesManager.getObstacles();
        obstaclesManager.getAppropriateObstacleType();

        if (obstacles.isEmpty()) {
            assertNotNull(obstaclesManager.getAppropriateObstacleType());
        }

    }

    @Test
    public void setPosition() {
    }

    @Test
    public void clearOldObstacles() {

    }
}