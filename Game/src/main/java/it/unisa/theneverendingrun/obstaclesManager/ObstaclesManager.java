package it.unisa.theneverendingrun.obstaclesManager;

import com.badlogic.gdx.Gdx;
import it.unisa.theneverendingrun.models.obstacles.*;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class ObstaclesManager {

    private static final int OFFSET = (int) (0.0625 * Gdx.graphics.getHeight());

    /**
     * Values which are needed to set the correct position of the new obstacle.
     */
    private float maxJumpingHeight;
    private float standingHeight;
    private float slidingHeight;
    private float standingWidth;

    /**
     * Reference to the obstacleFactory.
     */
    private static ObstacleFactory obstacleFactory;

    /**
     * List with all the obstacles actually present on the screen. The linked list keeps the order of insertion,
     * so only the first element is checked to be actually visible.
     */
    private static LinkedList<AbstractObstacle> obstacles = new LinkedList<AbstractObstacle>();

    /**
     * Private constructor. Only one obstacle manager must be present at a given time.
     */
    public ObstaclesManager(float maxJumpingHeight, float standingHeight, float slidingHeight, float standingWidth) {
        this.maxJumpingHeight = maxJumpingHeight;
        this.standingHeight = standingHeight;
        this.slidingHeight = slidingHeight;
        this.standingWidth = standingWidth;
        //obstacleFactory = new ObstacleFactory(maxJumpingHeight,slidingDistance,0);//fixme
    }

    /***
     * This method is used to get the right type of obstacle that can be added to the path, following the conditions.
     * For example, if the last obstacle was a slidable one, we cannot put another right after it,
     * otherwise the player might not be able to pass.
     * Please, note that this method randomly decides to add or not an obstacle, even if it can added.
     * @return The obstacle that can be added, null if none. //fixme maybe raise an exception?
     */
    public ObstacleType getAppropriateObstacleType() {
        //If there isn't any obstacle on the screen, add one at random
        if (obstacles.isEmpty()) {
            int random = ThreadLocalRandom.current().nextInt(ObstacleType.values().length);
            return ObstacleType.values()[random];
        }

        // Calculate the distance from the last obstacle. This distance is defined as the distance from the right
        // side of an obstacle to the left side of the view.
        AbstractObstacle lastObstacle = obstacles.getLast();
        double distance = Gdx.graphics.getWidth() - lastObstacle.getX() - lastObstacle.getWidth();

        // If distance is less than zero, the obstacle is still not completely visible, so wait
        if (distance < 0)
            return null;

        // If distance is zero, then we could add a jumpable obstacle, but only if the previous was of this type
        if (distance == 0) {
            if (lastObstacle instanceof JumpableObstacle) {
                if (ThreadLocalRandom.current().nextBoolean()) { //fixme tune the probability
                    return ObstacleType.Jumpable;
                }
            }
            if (lastObstacle instanceof JumpableSlidableObstacle) {
                return null;
            }
            if (lastObstacle instanceof SlidableObstacle) {
                return null;
            }
        }

        //If the space is not sufficient for the hero to pass, wait.
        if (distance > 0 && distance < standingWidth * 2) {//fixme tune the distance
            return null;
        }

        // If the obstacle is distant enough, it is possible to add every type of obstacle
        if (distance >= standingWidth * 5) {//fixme tune the probability and the distance
            if (ThreadLocalRandom.current().nextInt() % 50 == 0) {
                int random = ThreadLocalRandom.current().nextInt(0, ObstacleType.values().length);
                return ObstacleType.values()[random];
            }
        }
        return null;
    }

    public void setPosition(AbstractObstacle obstacle) {
        int yPosition;
        if (obstacle instanceof JumpableObstacle) {
            yPosition = ThreadLocalRandom.current().nextInt(OFFSET, (int) maxJumpingHeight + OFFSET);
        } else if (obstacle instanceof SlidableObstacle) {
            yPosition = ThreadLocalRandom.current().nextInt(OFFSET + (int) (slidingHeight / 2), OFFSET + (int) standingHeight);
        } else if (obstacle instanceof JumpableSlidableObstacle) {
            yPosition = ThreadLocalRandom.current().nextInt(OFFSET, (int) maxJumpingHeight + OFFSET);
        } else {
            yPosition = 0;
        }
        obstacle.setPosition(Gdx.graphics.getWidth(), yPosition);
    }

    public void clearOldObstacles() {
        if (obstacles.isEmpty())
            return;
        for (AbstractObstacle obs : obstacles) {
            if (!obs.isVisible()) {
                obstacles.remove(obs);
            }
        }
    }
}
