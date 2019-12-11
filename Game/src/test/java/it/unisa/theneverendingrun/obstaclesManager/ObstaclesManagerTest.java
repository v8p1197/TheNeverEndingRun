package it.unisa.theneverendingrun.obstaclesManager;

import de.tomgrill.gdxtesting.GdxTestRunner;
import it.unisa.theneverendingrun.models.obstacles.AbstractObstacle;
import it.unisa.theneverendingrun.models.obstacles.JumpableObstacle;
import it.unisa.theneverendingrun.models.obstacles.ObstacleFactory;
import it.unisa.theneverendingrun.models.obstacles.ObstacleType;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)

public class ObstaclesManagerTest {

    private static int OFFSET_MEASURE = 100;

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

        ObstaclesManager obstaclesManager = new ObstaclesManager(maxJumpingHeight, standingHeight, maxSlidingDistance, slidingHeight, standingWidth);

        //add a first obstacle. since we want to test the case in which the last obstacle is jumpable,
        // get new obstacle until the one returned is jumpable
        AbstractObstacle firstObstacle = obstaclesManager.getNewAppropriateObstacle();
        firstObstacle = obstaclesManager.getNewAppropriateObstacle();
        while (!(firstObstacle instanceof JumpableObstacle)) {
            //remove the last obstacle
            obstaclesManager.getObstacles().removeLast();
            firstObstacle = obstaclesManager.getNewAppropriateObstacle();
        }

        //move the rigth edge of the obstacle to the right edge of the window
        firstObstacle.setPosition(firstObstacle.getX() - firstObstacle.getWidth() + 1, firstObstacle.getY());

        //the new obstacle type will be null or jumpable
        ObstacleType newType = obstaclesManager.getAppropriateObstacleType();
        assertTrue(newType == null || newType == ObstacleType.Jumpable);

    }

    /*
    @Test
    public void testSlidableAfterJumpable() {

        ObstaclesManager obstaclesManager = new ObstaclesManager(maxJumpingHeight, standingHeight, maxSlidingDistance, slidingHeight, standingWidth);

        //add a first obstacle. since we want to test the case in which the last obstacle is jumpable,
        // get new obstacle until the one returned is jumpable
        AbstractObstacle firstObstacle = obstaclesManager.getNewAppropriateObstacle();
        firstObstacle = obstaclesManager.getNewAppropriateObstacle();
        while (!(firstObstacle instanceof JumpableObstacle)) {
            //remove the last obstacle
            obstaclesManager.getObstacles().removeLast();
            firstObstacle = obstaclesManager.getNewAppropriateObstacle();
        }

        //move the rigth edge of the obstacle to the right edge of the window
        firstObstacle.setPosition(firstObstacle.getX() - firstObstacle.getWidth() + 1, firstObstacle.getY());

        //the new obstacle type will be null or slidable
        ObstacleType newType = obstaclesManager.getAppropriateObstacleType();
        assertTrue(newType == null || newType == ObstacleType.Slidable);

        AbstractObstacle secondObstacle = obstaclesManager.getNewAppropriateObstacle();
        while (!(secondObstacle instanceof SlidableObstacle)) {
            //remove the last obstacle
            obstaclesManager.getObstacles().removeLast();
            secondObstacle = obstaclesManager.getNewAppropriateObstacle();
        }
        assertTrue(secondObstacle.getY() >= firstObstacle.getY() + firstObstacle.getHeight() + slidingHeight);

    }
    */

    @Test
    public void notEnoughSpaceTest() {
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
    public void setPositionJumpableTest() {

        ObstacleFactory obstacleFactory = new ObstacleFactory(maxJumpingHeight, slidingHeight, standingWidth);
        AbstractObstacle obs = obstacleFactory.getObstacle(ObstacleType.Jumpable, 0, 0);

        ObstaclesManager obstaclesManager = new ObstaclesManager(maxJumpingHeight, standingHeight, maxSlidingDistance, slidingHeight, standingWidth);
        obstaclesManager.setPosition(obs);

        //now we have an obstacle positioned at a certain height, we must check that it is jumpable
        assertTrue(obs.getY() + obs.getHeight() + ObstaclesManager.OFFSET <= maxJumpingHeight);
    }

    @Test
    public void setPositionSlidableTest() {

        ObstacleFactory obstacleFactory = new ObstacleFactory(maxJumpingHeight, slidingHeight, standingWidth);
        AbstractObstacle obs = obstacleFactory.getObstacle(ObstacleType.Slidable, 0, 0);

        ObstaclesManager obstaclesManager = new ObstaclesManager(maxJumpingHeight, standingHeight, maxSlidingDistance, slidingHeight, standingWidth);
        obstaclesManager.setPosition(obs);

        //now we have an obstacle positioned at a certain height, we must check that it is slidable
        assertTrue(obs.getY() + ObstaclesManager.OFFSET >= slidingHeight);
    }

    @Test
    public void setPositionJumpableSlidableTest() {

        ObstacleFactory obstacleFactory = new ObstacleFactory(maxJumpingHeight, slidingHeight, standingWidth);
        AbstractObstacle obs = obstacleFactory.getObstacle(ObstacleType.JumpableSlidable, 0, 0);

        ObstaclesManager obstaclesManager = new ObstaclesManager(maxJumpingHeight, standingHeight, maxSlidingDistance, slidingHeight, standingWidth);
        obstaclesManager.setPosition(obs);

        //now we have an obstacle positioned at a certain height, we must check that it is slidable
        assertTrue((obs.getY() + obs.getHeight() + ObstaclesManager.OFFSET <= maxJumpingHeight) || (obs.getY() + ObstaclesManager.OFFSET >= slidingHeight));
    }



}