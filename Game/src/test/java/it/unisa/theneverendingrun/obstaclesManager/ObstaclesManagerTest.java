package it.unisa.theneverendingrun.obstaclesManager;

import de.tomgrill.gdxtesting.GdxTestRunner;
import it.unisa.theneverendingrun.models.obstacles.AbstractObstacle;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.LinkedList;
import java.util.Random;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
        // we cannot add anything
        obstaclesManager.getNewAppropriateObstacle();
        assertNull(obstaclesManager.getNewAppropriateObstacle());
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